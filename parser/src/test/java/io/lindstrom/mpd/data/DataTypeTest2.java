package io.lindstrom.mpd.data;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.lindstrom.mpd.data.descriptor.GenericDescriptor;
import io.lindstrom.mpd.data.descriptor.Role;
import io.lindstrom.mpd.data.descriptor.protection.Mp4Protection;
import io.lindstrom.mpd.data.descriptor.protection.PlayReadyContentProtection;
import io.lindstrom.mpd.data.descriptor.protection.WidewineProtection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class DataTypeTest2 {
    private final Class<?> clazz;
    private final Class<?> builderClazz;

    public DataTypeTest2(Class<?> clazz) {
        this.clazz = clazz;
        this.builderClazz = builderClass(clazz);
    }

    @Test
    public void builderTest() {
        assertNotNull(clazz.getName() + " should have a builder", builderClazz);

        List<Field> builderFields = Arrays.stream(clazz.getDeclaredFields())
                .filter(field -> !Modifier.isStatic(field.getModifiers()))
                .sorted(Comparator.comparing(Field::getName))
                .collect(Collectors.toList());

        List<Field> props = properties();
        props.sort(Comparator.comparing(Field::getName));

        assertEquals(clazz.getName() + ": All fields should be present in builder", props, builderFields);
    }

    @Test
    public void staticBuilderFunction() {
        assertTrue(clazz.getName() + ": A static builder() method should exists",
                Arrays.stream(clazz.getDeclaredMethods())
                        .filter(m -> Modifier.isStatic(m.getModifiers()) && Modifier.isPublic(m.getModifiers()))
                        .anyMatch(m -> m.getName().equals("builder")));

        assertTrue(clazz.getName() + ": A buildUpon() method should exists",
                Arrays.stream(clazz.getDeclaredMethods())
                        .filter(m -> !Modifier.isStatic(m.getModifiers()) && Modifier.isPublic(m.getModifiers()))
                        .anyMatch(m -> m.getName().equals("buildUpon")));
    }

    @Test
    public void noArgsConstructor() {
        if (clazz.getAnnotation(JsonDeserialize.class) != null) {
            return;
        }

        assertTrue(clazz.getName() + "should have a private constructor without arguments",
                Arrays.stream(clazz.getDeclaredConstructors())
                        .filter(c -> Modifier.isPrivate(c.getModifiers()) || Modifier.isProtected(c.getModifiers()))
                        .anyMatch(c -> c.getParameterCount() == 0));
    }

    @Test
    public void finalFields() {
        assertTrue(clazz.getName() + ": all properties should be final",
                properties().stream().allMatch(f -> Modifier.isFinal(f.getModifiers())));
    }

    private List<Field> properties() {
        return Arrays.stream(clazz.getDeclaredFields())
                .filter(field -> !Modifier.isStatic(field.getModifiers()))
                .collect(Collectors.toList());
    }


    @Parameters
    public static List<Class<?>> params() {
        return Arrays.asList(
                AdaptationSet.class,
                BaseURL.class,
                ContentComponent.class,
                Event.class,
                EventStream.class,
                FrameRate.class,
                Metrics.class,
                MPD.class,
                Period.class,
                Profiles.class,
                ProgramInformation.class,
                Range.class,
                Ratio.class,
                Representation.class,
                Segment.class,
                SegmentBase.class,
                SegmentList.class,
                SegmentTemplate.class,
                SegmentURL.class,
                SubRepresentation.class,
                Subset.class,
                URLType.class,
                UTCTiming.class,
                GenericDescriptor.class,
                Role.class,
                Mp4Protection.class,
                PlayReadyContentProtection.class,
                WidewineProtection.class);
    }

    private static Class<?> builderClass(Class<?> clazz) {
        try {
            return Class.forName(clazz.getName() + "$Builder");
        } catch (ClassNotFoundException e) {
            return null;
        }
    }
}
