package io.lindstrom.mpd.data;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.lindstrom.mpd.data.descriptor.GenericDescriptor;
import io.lindstrom.mpd.data.descriptor.Role;
import io.lindstrom.mpd.data.descriptor.protection.Mp4Protection;
import io.lindstrom.mpd.data.descriptor.protection.PlayReadyContentProtection;
import io.lindstrom.mpd.data.descriptor.protection.WidewineProtection;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;


public class DataTypeTest2 {
    private Class<?> clazz;
    private Class<?> builderClazz;

    private Class<?> setClazz(Class<?> clazz) {
        this.clazz = clazz;
        return builderClass(clazz);
    }

    @ParameterizedTest
    @MethodSource("params")
    public void builderTest(Class<?> clazz) {
        assertNotNull(setClazz(clazz), clazz.getName() + " should have a builder");

        List<Field> builderFields = Arrays.stream(clazz.getDeclaredFields())
                .filter(field -> !Modifier.isStatic(field.getModifiers()))
                .sorted(Comparator.comparing(Field::getName))
                .collect(Collectors.toList());

        List<Field> props = properties();
        props.sort(Comparator.comparing(Field::getName));

        assertEquals(props, builderFields, clazz.getName() + ": All fields should be present in builder");
    }

    @ParameterizedTest
    @MethodSource("params")
    public void staticBuilderFunction(Class<?> clazz) {
        assertTrue(
                Arrays.stream(clazz.getDeclaredMethods())
                        .filter(m -> Modifier.isStatic(m.getModifiers()) && Modifier.isPublic(m.getModifiers()))
                        .anyMatch(m -> m.getName().equals("builder")),
                clazz.getName() + ": A static builder() method should exists");
        assertTrue(
                Arrays.stream(clazz.getDeclaredMethods())
                        .filter(m -> !Modifier.isStatic(m.getModifiers()) && Modifier.isPublic(m.getModifiers()))
                        .anyMatch(m -> m.getName().equals("buildUpon")), clazz.getName() + ": A buildUpon() method should exists");
    }

    @ParameterizedTest
    @MethodSource("params")
    public void noArgsConstructor(Class<?> clazz) {
        if (clazz.getAnnotation(JsonDeserialize.class) != null) {
            return;
        }

        assertTrue(
                Arrays.stream(clazz.getDeclaredConstructors())
                        .filter(c -> Modifier.isPrivate(c.getModifiers()) || Modifier.isProtected(c.getModifiers()))
                        .anyMatch(c -> c.getParameterCount() == 0), clazz.getName() + "should have a private constructor without arguments");
    }

    @ParameterizedTest
    @MethodSource("params")
    public void finalFields(Class<?> clazz) {
        setClazz(clazz);
        assertTrue(
                properties().stream().allMatch(f -> Modifier.isFinal(f.getModifiers())), clazz.getName() + ": all properties should be final");
    }

    private List<Field> properties() {
        return Arrays.stream(clazz.getDeclaredFields())
                .filter(field -> !Modifier.isStatic(field.getModifiers()))
                .collect(Collectors.toList());
    }


    private static List<Class<?>> params() {
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
