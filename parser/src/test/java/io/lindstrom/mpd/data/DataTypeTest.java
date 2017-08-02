package io.lindstrom.mpd.data;

import io.lindstrom.mpd.MPDParser;
import org.junit.Test;

import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class DataTypeTest {
    private static final String PACKAGE = DataTypeTest.class.getPackage().getName();

    @Test
    public void rebuildMPD() throws Exception {
        MPD mpd = new MPDParser().parse(Files.newInputStream(Paths.get("src/test/resources/random.mpd")));
        assertEquals(mpd, rebuildAndValidate(mpd));
    }

    private Object rebuildAndValidate(Object object) throws Exception {
        // If the object is a list, rebuild all elements
        if (object instanceof List) {
            List<Object> list = new ArrayList<>();
            for (Object member : (List<?>) object) {
                list.add(rebuildAndValidate(member));
            }
            return list;
        }

        Class<?> clazz = object.getClass();
        if (!clazz.getPackage().getName().equals(PACKAGE) || clazz.isEnum()) {
            return object;
        }

        // Check that hashCode, toString and equals are defined
        assertNotNull(clazz.getDeclaredMethod("hashCode").invoke(object));
        assertNotNull(clazz.getDeclaredMethod("toString").invoke(object));
        assertTrue(clazz.getDeclaredMethod("equals", Object.class).invoke(object, object)
                .equals(Boolean.TRUE));

        Method buildUpon = clazz.getDeclaredMethod("buildUpon");
        Class<?> builderType = buildUpon.getReturnType();
        Object builder = buildUpon.invoke(object);

        for (Method method : builderType.getMethods()) {
            if (method.getName().startsWith("with")) {
                String getter = method.getName().replaceFirst("with", "get");

                Object value = clazz.getMethod(getter).invoke(object);
                Object newValue = rebuildAndValidate(value);

                method.invoke(builder, newValue);
            }
        }

        return builderType.getMethod("build").invoke(builder);
    }
}
