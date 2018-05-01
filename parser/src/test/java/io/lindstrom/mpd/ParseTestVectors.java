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

import static org.junit.runners.Parameterized.*;

@RunWith(Parameterized.class)
public class ParseTestVectors {
    private static final MPDParser PARSER = new MPDParser();
    private static final DifferenceEvaluator DIFFERENCE_EVALUATOR = new MyDifferenceEvaluator();
    private final Path path;

    public ParseTestVectors(Path path) {
        this.path = path;
    }

    @Test
    public void similarVectors() throws IOException {
        MPD mpd = PARSER.parse(Files.newInputStream(path));
        String actual = PARSER.writeAsString(mpd);

        Assert.assertThat(path + " is similar", actual,
                CompareMatcher.isSimilarTo(Input.fromFile(path.toFile()))
                        .withDifferenceEvaluator(DIFFERENCE_EVALUATOR)
                        .ignoreComments()
                        .ignoreWhitespace());
    }

    @Parameters
    public static List<Path> params() throws IOException {
        try (Stream<Path> paths = Files.list(Paths.get("src/test/resources/vectors/"))) {
            return paths.collect(Collectors.toList());
        }
    }
}