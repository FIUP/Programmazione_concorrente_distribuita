package statistics;

import static org.junit.Assert.*;

import org.junit.Test;

import statistics.MultiMap;

public class MultiMapUnitTest {

	@Test
	public void testMultiMap() {
		MultiMap<Integer, Integer> map = new MultiMap<Integer, Integer>();
		map.put(1, 1);
		map.put(1, 2);
		
		assertEquals("Should have 2 values under key 1", true, map.get(1).size() == 2);
		assertEquals("Should have a single key in the map", true, map.keySet().size() == 1);
	}
}