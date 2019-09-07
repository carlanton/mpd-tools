package io.lindstrom.mpd;

import io.lindstrom.mpd.data.MPD;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
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

import static org.hamcrest.MatcherAssert.assertThat;


public class ParseTestVectors {
    private static final MPDParser PARSER = new MPDParser();
    private static final DifferenceEvaluator DIFFERENCE_EVALUATOR = new MyDifferenceEvaluator();

    @ParameterizedTest
    @MethodSource("params")
    public void similarVectors(Path path) throws IOException {

        MPD mpd = PARSER.parse(Files.newInputStream(path));
        String actual = PARSER.writeAsString(mpd);

        assertThat(path + " is similar", actual,
                CompareMatcher.isSimilarTo(Input.fromFile(path.toFile()))
                        .withDifferenceEvaluator(DIFFERENCE_EVALUATOR)
                        .ignoreComments()
                        .ignoreWhitespace());
    }

    private static List<Path> params() throws IOException {
        try (Stream<Path> paths = Files.list(Paths.get("src/test/resources/vectors/"))) {
            return paths.collect(Collectors.toList());
        }
    }
}