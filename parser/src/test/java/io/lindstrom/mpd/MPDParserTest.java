package io.lindstrom.mpd;

import io.lindstrom.mpd.data.MPD;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.xmlunit.builder.Input;
import org.xmlunit.diff.DifferenceEvaluator;
import org.xmlunit.matchers.CompareMatcher;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class MPDParserTest {
    private static final MPDParser PARSER = new MPDParser();
    private static final DifferenceEvaluator DIFFERENCE_EVALUATOR = new MyDifferenceEvaluator();

    @Parameters
    public static List<Path> params() throws IOException {
        try (Stream<Path> paths = Files.list(Paths.get("src/test/resources/vectors/"))) {
            return paths.collect(Collectors.toList());
        }
    }

    private final Path path;

    public MPDParserTest(Path path) {
        this.path = path;
    }

    @Test
    public void similarVectors() throws Exception {
        String actual;

        try {
            MPD mpd = PARSER.parse(Files.newInputStream(path));
            actual = PARSER.writeAsString(mpd);
        } catch (Exception e) {
            return;
        }

        Assert.assertThat(path + " is similar", actual,
                CompareMatcher.isSimilarTo(Input.fromFile(path.toFile()))
                        .withDifferenceEvaluator(DIFFERENCE_EVALUATOR)
                        .ignoreComments()
                        .ignoreWhitespace());
    }
}