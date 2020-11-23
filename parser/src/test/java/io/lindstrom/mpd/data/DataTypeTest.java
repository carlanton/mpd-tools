package io.lindstrom.mpd.data;

import io.lindstrom.mpd.MPDParser;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DataTypeTest {
    private static final String PACKAGE = DataTypeTest.class.getPackage().getName();

    @Test
    public void rebuildMPD() throws Exception {
        MPD mpd = new MPDParser().parse(Files.newInputStream(Paths.get("src/test/resources/random.mpd")));
        assertEquals(mpd, rebuildAndValidate(mpd, ""));
    }

    private Object rebuildAndValidate(Object object, String path) throws Exception {
        if (object == null) {
            return null;
        }

        // If the object is a list, rebuild all elements
        if (object instanceof List) {

            // Make sure that the list is immutable
            String name = object.getClass().getName().split("\\$")[0];
            assertEquals("java.util.ImmutableCollections", name, "List is immutable " + path);

            List<Object> list = new ArrayList<>();
            for (Object member : (List<?>) object) {
                list.add(rebuildAndValidate(member, path + "/" + object.getClass().getSimpleName()));
            }
            return list;
        }

        Class<?> clazz = object.getClass();
        if (!clazz.getPackage().getName().equals(PACKAGE) || clazz.isEnum()) {
            return object;
        }

        validate(object, clazz);

        Method buildUpon = clazz.getDeclaredMethod("buildUpon");
        Class<?> builderType = buildUpon.getReturnType();
        Object builder = buildUpon.invoke(object);

        for (Method method : builderType.getMethods()) {
            if (method.getName().startsWith("with")) {
                String getterName = method.getName().replaceFirst("with", "get");

                Method getter;
                try {
                    getter = clazz.getMethod(getterName);
                } catch (NoSuchMethodException e) {
                    continue;
                }

                if (!getter.getReturnType().equals(method.getParameterTypes()[0])) {
                    continue;
                }

                Object value = getter.invoke(object);
                Object newValue = rebuildAndValidate(value, path + "/" + method.getName().replace("with", ""));


                method.invoke(builder, newValue);
            }
        }

        return builderType.getMethod("build").invoke(builder);
    }

    private void validate(Object object, Class<?> clazz) throws Exception {
        for (Field field : FieldUtils.getAllFields(clazz)) {
            int modifiers = field.getModifiers();

            // Ignore static fields
            if (Modifier.isStatic(modifiers)) {
                continue;
            }

            assertTrue(Modifier.isPrivate(modifiers), "Field is private");
            assertTrue(Modifier.isFinal(modifiers), "Field is final");

            Method getter = clazz.getMethod(getterName(field.getName()));
            assertNotNull(getter, "Getter exists");
            assertTrue(Modifier.isPublic(getter.getModifiers()), "Getter is public");
        }

        // Check that hashCode, toString and equals are defined
        assertNotNull(clazz.getDeclaredMethod("hashCode").invoke(object));
        assertNotNull(clazz.getDeclaredMethod("toString").invoke(object));
        assertTrue((Boolean) clazz.getDeclaredMethod("equals", Object.class).invoke(object, object));
    }

    private static String getterName(String fieldName) {
        return "get" +
                fieldName.substring(0, 1).toUpperCase() +
                fieldName.substring(1);
    }
}
