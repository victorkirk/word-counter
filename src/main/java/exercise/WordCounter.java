package exercise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Reads words from an input source and provides counts of words by word length.
 */
public class WordCounter {

    /**
     * A regex that defines which chars are to be considered part of a word.
     */
    private static final String WORD_CHARS_REGEX = "[^a-zA-Z0-9&/]";

    public Map<Integer, Long> count(InputStream is) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            return count(reader.lines());
        }
    }

    public Map<Integer, Long> count(Path path) throws IOException {
        return count(Files.lines(path));
    }

    public Map<Integer, Long> count(URL path) throws IOException {
        return count(path.openStream());
    }

    public Map<Integer, Long> count(Stream<String> stream) throws IOException {
        return stream
                .flatMap(line -> Arrays.stream(line.split("\\s+")))
                .map(w -> w.replaceAll(WORD_CHARS_REGEX, ""))
                .filter(Predicate.not(String::isEmpty))
                .filter(Predicate.not(String::isBlank))
                .collect(Collectors.groupingBy(String::length, Collectors.counting()));
    }
}