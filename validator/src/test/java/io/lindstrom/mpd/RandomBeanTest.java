package io.lindstrom.mpd;

import io.github.benas.randombeans.EnhancedRandomBuilder;
import io.github.benas.randombeans.FieldDefinitionBuilder;
import io.github.benas.randombeans.api.EnhancedRandom;
import io.lindstrom.mpd.data.MPD;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.function.Supplier;
import java.util.stream.Stream;

@RunWith(Parameterized.class)
public class RandomBeanTest {
    @Parameters
    public static Iterable<MPD> randomBeans() {
        EnhancedRandom random = new MPDRandomBuilder()
                .constantStringField("segmentAlignment", "true")
                .constantStringField("subsegmentAlignment", "true")
                .constantStringField("lang", "sv")
                .constantStringField("contains", "1")
                .constantStringField("dependencyLevel", "1")
                .randomize(Long.class, (Supplier<Long>) () -> 5L)
                .collectionSizeRange(1, 2)
                .seed(10005)
                .build();

        return () -> Stream.generate(() -> random.nextObject(MPD.class))
                .limit(10).iterator();
    }

    private final MPD mpd;

    public RandomBeanTest(MPD mpd) {
        this.mpd = mpd;
    }

    @Test
    public void randomMPD() throws IOException, SAXException {
        MPDValidator validator = new MPDValidator();
        MPDParser parser = new MPDParser();

        String generated = parser.writeAsString(parser.parse(parser.writeAsString(mpd)));

        try {
            validator.validate(generated);
        } catch (Exception e) {
            System.out.println(generated);
            throw e;
        }
    }

    static class MPDRandomBuilder extends EnhancedRandomBuilder {
        MPDRandomBuilder constantStringField(String fieldName, String value) {
            randomize(FieldDefinitionBuilder.field()
                    .named(fieldName).ofType(String.class).get(), (Supplier<String>) () -> value);
            return this;
        }
    }
}
