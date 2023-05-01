/**
 * This class tests the functionality of the MultiHashMap class
 * 
 * @author Luke Kvamme, Marc D'Avanzo
 * @date 4/25/2022
 */
package HashTable;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.junit.jupiter.api.Test;

class MultiHashMapTest
{
    /**
     * Basic testing of MultiHashMap
     * 
     * Tests put(), size(), ContainsPair(), KeySet(), and Contains()
     */
	@Test
	void basic_test() {
		MultiHashMap<Integer, String> map = new MultiHashMap<Integer, String>();

		map.put(1, "a");
		map.put(2, "b");
		map.put(3, "c");
		map.put(1, "d");

		// Sizes
		assertFalse(map.isEmpty());
		assertEquals(4, map.size());
		
		// Containment
		assertTrue(map.contains(1));
		assertFalse(map.contains(-1));

		assertTrue(map.containsPair(1, "a"));
		assertFalse(map.containsPair(1, "A"));
		assertTrue(map.containsPair(1, "d"));
		assertFalse(map.containsPair(1, "D"));
		
		// keys
		List<Integer> mykeys = Arrays.asList(1, 2, 3);
		int mapKeyCount = 0;
		
		for (int map_key : map.keySet())
		{
			assertTrue(mykeys.contains(map_key));
			mapKeyCount++;
		}
		assertEquals(mykeys.size(), mapKeyCount);
	}
	
	/**
	 * Tests the getAll() using 
	 * put(), size(), ContainsPair(), KeySet(), and Contains()
	 */
	@Test
	void getAll_test() {
		MultiHashMap<Integer, String> map = new MultiHashMap<Integer, String>();

		map.put(1, "a");
		map.put(2, "b");
		map.put(3, "c");
		map.put(1, "d");
		map.put(1, "e");
		map.put(2, "f");
		map.put(3, "g");
		map.put(1, "h");

		// Sizes
		assertEquals(8, map.size());
		
		// Containment
		assertTrue(map.contains(1));
		assertFalse(map.contains(-1));

		assertTrue(map.containsPair(1, "h"));
		assertTrue(map.containsPair(3, "g"));
		
		// keys
		List<Integer> mykeys = Arrays.asList(1, 2, 3);
		int mapKeyCount = 0;
		
		for (Integer k : map.keySet())
		{
			assertTrue(mykeys.contains(k));
			mapKeyCount++;
		}
		assertEquals(mykeys.size(), mapKeyCount);
	}

	/**
	 * tests deleteAll() by doing factors of five to increase
	 * chain length, puts keys and values and deletes everything. 
	 */
	@Test
	void fives_test() {
		MultiHashMap<Integer, String> map = new MultiHashMap<Integer, String>();
		List<String> values = Arrays.asList("a", "b", "c", "d", "e", "f", "g", "h", "i", "j");
		final int LAST_KEY = 100000;

		//
		// SIZING UP
		//
		for (int KEY = 0; KEY < LAST_KEY; KEY += 5)
		{
			for (String value : values)
			{
				map.put(KEY, value);
			}
		}

		// Sizes
		assertEquals(values.size() * 20,000, map.size());
		
		//
		// SIZING DOWN
		//
		for (int KEY = 0; KEY <= LAST_KEY; KEY += 5)
		{
			map.deleteAll(KEY);
		}
		
		// Sizes
		assertEquals(0, map.size());	
	}
		

	@Test
	void test_resize_up_and_down()
	{
		MultiHashMap<Integer, String> map = new MultiHashMap<Integer, String>();
		List<String> values = Arrays.asList("a", "b", "c", "d", "e", "f", "g", "h", "i", "j");
		final int LAST_KEY = 100000;

		//
		// SIZING UP
		//
		for (int KEY = 1; KEY <= LAST_KEY; KEY++)
		{
			for (String value : values)
			{
				map.put(KEY, value);
			}
		}

		// Sizes
		assertEquals(values.size() * LAST_KEY, map.size());

		//
		// SIZING DOWN
		//
		for (int KEY = 1; KEY <= LAST_KEY; KEY++)
		{
			map.deleteAll(KEY);
		}
		
		// Sizes
		assertEquals(0, map.size());	
	}
}
