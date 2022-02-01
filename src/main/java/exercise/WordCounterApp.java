package exercise;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Map;

public class WordCounterApp {

    public static void main(String[] args) {
        WordCounter counter = new WordCounter();

        if (args.length == 0) {
            String url = "https://janelwashere.com/files/bible_daily.txt";
            System.out.println("No args specifiied, defaulting to demo URL " + url);
            args = new String[] { url };
        }

        for (String arg: args) {
            try {
                // A rather crude check to see if we are looking for a file or URL
                Map<Integer, Long> wordCounts = arg.startsWith("http")
                        ? counter.count(new URL(arg))
                                : counter.count(Paths.get(arg));
                WordCountStatistics stats = new WordCountStatistics(wordCounts);
                System.out.println(arg + ":");
                System.out.println(stats);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
