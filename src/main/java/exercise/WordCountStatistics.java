package exercise;

import java.util.LongSummaryStatistics;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Takes a count of words by word lenght and generates some statics based on them.
 */
public class WordCountStatistics {

	private Map<Integer, Long> counts;

	private long total;

	private double average;

	private long max;

	// Ideally I would use lombok for these getters, but to simplify IDE usage I have manually
	// generated these.
	public Map<Integer, Long> getCounts() {
		return counts;
	}

	/**
	 * Gets the total number of words.
	 *
	 * @return the overall word total.
	 */
	public long getTotal() {
		return total;
	}

	/**
	 * Gets the average number of chars per word.
	 *
	 * @return the word average character length.
	 */
	public double getAverage() {
		return average;
	}

	/**
	 * Gets the largest number of word sizes by character length.
	 *
	 * @return the word average character length.
	 */
	public long getMax() {
		return max;
	}

	/**
	 * Initialises a new instance and computes some basic stats.
	 *
	 * @param counts a map with the number of chars in a word as the key and the number
	 * of words that occurred for that length as the value.
	 */
	public WordCountStatistics(Map<Integer, Long> counts) {
		if (counts == null) {
			throw new IllegalArgumentException("counts must not be null");
		}

		this.counts = counts;

		LongSummaryStatistics stats = counts.entrySet().stream()
				.mapToLong(e -> e.getValue())
				.summaryStatistics();

		this.total = stats.getSum();
		this.max = stats.getMax() != Long.MIN_VALUE ? stats.getMax() : 0;
		this.average = this.total == 0 ? 0 :
			(double) counts.entrySet().stream()
			.mapToLong(e -> e.getValue() * e.getKey())
			.sum() / this.total;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append("Word count = " + total).append("\n");
		sb.append("Average word length = " + average).append("\n");

		counts.forEach((k, v) -> countItemToString(sb, k, v));

		sb.append("The most frequently occurring word length is ");
		sb.append(this.max);
		sb.append(" for word length(s) of ");
		sb.append(counts.entrySet().stream()
				.filter(e -> e.getValue() == this.max)
				.map(e -> Integer.toString(e.getKey()))
				.collect(Collectors.joining(" & ")));

		return sb.toString();
	}

	private StringBuilder countItemToString(StringBuilder sb, Integer k, Long v) {
		return sb.append("Number of words of length ").append(k).append(" is ").append(v).append("\n");
	}
}
