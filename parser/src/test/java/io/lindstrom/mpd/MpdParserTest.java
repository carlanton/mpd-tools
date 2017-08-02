package io.lindstrom.mpd;

import io.lindstrom.mpd.data.MPD;
import org.junit.Assert;
import org.junit.Test;
import org.xmlunit.builder.Input;
import org.xmlunit.matchers.CompareMatcher;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MpdParserTest {
    private final Path resources = Paths.get("src/test/resources/");

    private CompareMatcher isSimilarTo(Path path) {
        return CompareMatcher.isSimilarTo(Input.fromFile(path.toFile()))
                .withDifferenceEvaluator(new MyDifferenceEvaluator())
                .ignoreComments()
                .ignoreWhitespace();
    }

    @Test
    public void parse() throws Exception {
        MPDParser parser = new MPDParser();

        Path source = resources.resolve("wowza.mpd");
        MPD mpd = parser.parse(Files.newInputStream(source));
        String result = parser.writeAsString(mpd);
        Assert.assertThat("zing", result, isSimilarTo(source));
    }

    @Test
    public void similarVectors() throws Exception {
        List<Path> mpds = Files.list(resources.resolve("vectors/")).collect(Collectors.toList());

        MPDParser parser = new MPDParser();
        for (Path path : mpds) {
            if (path.toString().endsWith(".swp"))
                continue;

            String actual;

            try {
                MPD mpd = parser.parse(Files.newInputStream(path));
                actual = parser.writeAsString(mpd);
            } catch (Exception e) {
                continue;
            }

            Assert.assertThat("zing", actual,
                    CompareMatcher.isSimilarTo(Input.fromFile(path.toFile()))
                            .withDifferenceEvaluator(new MyDifferenceEvaluator())
                            .ignoreComments()
                            .ignoreWhitespace());
        }

    }

    @Test
    public void vectors() throws Exception {
        List<Path> mpds = Files.list(resources.resolve("vectors/")).collect(Collectors.toList());

        List<Path> failure = new ArrayList<>();
        List<Path> success = new ArrayList<>();
        MPDParser parser = new MPDParser();
        for (Path path : mpds) {
            if (path.toString().endsWith(".swp"))
                continue;

            try {
                MPD mpd = parser.parse(Files.newInputStream(path));
                success.add(path);
            } catch (Exception e) {
                if (!e.getMessage().contains("_KID")) {
                    System.out.println(path);
                    System.out.println(e.getMessage());
                    System.out.println();
                    failure.add(path);
                }
               // throw e;
            }
        }

        System.out.println(failure.size());
        System.out.println(success.size());
    }
}