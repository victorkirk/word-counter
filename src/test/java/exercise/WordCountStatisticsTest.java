package exercise;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;

import org.junit.Test;

public class WordCountStatisticsTest {

    @Test(expected = IllegalArgumentException.class)
    public void testNull() {
        new WordCountStatistics(null);
    }

    @Test
    public void testEmpty() {
        HashMap<Integer, Long> counts = new HashMap<>();

        WordCountStatistics stats = new WordCountStatistics(counts);
        assertEquals("Total is non zero for empty count", 0, stats.getTotal());
        assertEquals("Max is non zero for empty count", 0, stats.getMax());
        assertEquals("Average is non zero for empty count", 0, stats.getAverage(), 0.01);
    }

    @Test
    public void testWithData() {
        HashMap<Integer, Long> counts = new HashMap<>();
        counts.put(1, 2L);  // 2
        counts.put(3, 6L);  // 18
        counts.put(5, 6L);  // 30
        counts.put(6, 2L);  // 12
        counts.put(4, 5L);  // 20

        long total = 0;
        long max = 0;
        double avg = 0;

        for (var e: counts.entrySet()) {
            total += e.getValue();
            avg += e.getValue() * e.getKey();
            max = Math.max(max, e.getValue());
        }
        avg /= total;

        WordCountStatistics stats = new WordCountStatistics(counts);
        assertEquals("Total word count is incorrect", total, stats.getTotal());
        assertEquals("Max is incorrect", max, stats.getMax());
        assertEquals("Average word length is incorrect", avg, stats.getAverage(), 0.01);
    }
}
