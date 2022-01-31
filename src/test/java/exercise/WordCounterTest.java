package exercise;


import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class WordCounterTest {

	WordCounter counter;

	@Before
	public void setup() {
		counter = new WordCounter();
	}

	@Test
	public void testTestString() throws IOException {
		String text = "Hello world & good morning. The date is 18/05/2016";
		InputStream is = new ByteArrayInputStream(text.getBytes());
		Map<Integer, Long> counts = counter.count(is);

		Map<Integer, Long> expected = new HashMap<>();
		expected.put(1, 1L);
		expected.put(2, 1L);
		expected.put(3, 1L);
		expected.put(4, 2L);
		expected.put(5, 2L);
		expected.put(7, 1L);
		expected.put(10, 1L);

		assertEquals("Word counts do not match", expected, counts);
	}
}