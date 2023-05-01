package HashTable;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

class LinkedMultiMapTest
{

	@Test
	void test_basic()
	{
		LinkedMultiMap<Integer, String> map = new LinkedMultiMap<Integer, String>();

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
	
	@Test
	void test_redundant_add()
	{
		LinkedMultiMap<Integer, String> map = new LinkedMultiMap<Integer, String>();

		map.put(1, "a");
		map.put(2, "b");
		map.put(3, "c");
		map.put(1, "a");
		map.put(2, "b");
		map.put(3, "c");

		// Sizes
		assertFalse(map.isEmpty());
		assertEquals(3, map.size());
		
		assertTrue(map.containsPair(1, "a"));
		assertTrue(map.containsPair(2, "b"));
		assertTrue(map.containsPair(3, "c"));
		
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
	
	@Test
	void test_unique_keys()
	{
		LinkedMultiMap<Integer, String> map = new LinkedMultiMap<Integer, String>();

        List<String> valuesFor1 = Arrays.asList("a", "d", "e", "h");
        List<String> valuesFor2 = Arrays.asList("b", "f");
        List<String> valuesFor0 = Arrays.asList("z");
        List<String> valuesFor3 = Arrays.asList("c", "g");
        
        valuesFor1.forEach(s -> { map.put(1, s); } );
        valuesFor2.forEach(s -> { map.put(2, s); } );
        valuesFor0.forEach(s -> { map.put(0, s); } );
        valuesFor3.forEach(s -> { map.put(3, s); } );

        List<Integer> unique = Arrays.asList(0, 1, 2, 3);

		// Unique keys
		int keyCount = 0;
		
		for (Integer map_key : map.keySet())
		{
			assertTrue(unique.contains(map_key));
			keyCount++;
		}
		assertEquals(unique.size(), keyCount);
	}
	
	@Test
	void test_delete_all()
	{
		LinkedMultiMap<Integer, String> map = new LinkedMultiMap<Integer, String>();

        List<String> valuesFor1 = Arrays.asList("a", "d", "e", "h");
        List<String> valuesFor2 = Arrays.asList("b", "f");
        List<String> valuesFor0 = Arrays.asList("z");
        List<String> valuesFor3 = Arrays.asList("c", "g");
        
        valuesFor1.forEach(s -> { map.put(1, s); } );
        valuesFor2.forEach(s -> { map.put(2, s); } );
        valuesFor0.forEach(s -> { map.put(0, s); } );
        valuesFor3.forEach(s -> { map.put(3, s); } );

		// Sizes
		assertEquals(valuesFor1.size() +
				     valuesFor2.size() + 
				     valuesFor0.size() + 
				     valuesFor3.size(), map.size());

		
		map.deleteAll(2);
		assertEquals(valuesFor1.size() +
			         valuesFor0.size() + 
			         valuesFor3.size(), map.size());
	
		map.deleteAll(1);
		assertEquals(valuesFor0.size() + 
			         valuesFor3.size(), map.size());

		map.deleteAll(3);
		assertEquals(valuesFor0.size(), map.size());
		
		map.deleteAll(0);
		assertEquals(0, map.size());
	}
	
	@Test
	void test_get_basic()
	{
		LinkedMultiMap<Integer, String> map = new LinkedMultiMap<Integer, String>();

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
		for (int map_key : map.keySet())
		{
			assertTrue(mykeys.contains(map_key));
			mapKeyCount++;
		}
		assertEquals(mykeys.size(), mapKeyCount);
	}
	
	@Test
	void test_get_values()
	{
		LinkedMultiMap<Integer, String> map = new LinkedMultiMap<Integer, String>();

        List<String> valuesFor1 = Arrays.asList("a", "d", "e", "h");
        List<String> valuesFor2 = Arrays.asList("b", "f");
        List<String> valuesFor0 = Arrays.asList("z");
        List<String> valuesFor3 = Arrays.asList("c", "g");
        
        valuesFor1.forEach(s -> { map.put(1, s); } );
        valuesFor2.forEach(s -> { map.put(2, s); } );
        valuesFor0.forEach(s -> { map.put(0, s); } );
        valuesFor3.forEach(s -> { map.put(3, s); } );
        
		// Sizes
		assertEquals(valuesFor1.size() +
				     valuesFor2.size() + 
				     valuesFor0.size() + 
				     valuesFor3.size(), map.size());
		
		// values
		int valueCount = 0;
		for (String map_value : map.getAll(1))
		{
			assertTrue(valuesFor1.contains(map_value));
			valueCount++;
		}
		assertEquals(valuesFor1.size(), valueCount);
		
		valueCount = 0;
		for (String map_value : map.getAll(2))
		{
			assertTrue(valuesFor2.contains(map_value));
			valueCount++;
		}
		assertEquals(valuesFor2.size(), valueCount);
		
		valueCount = 0;
		for (String map_value : map.getAll(3))
		{
			assertTrue(valuesFor3.contains(map_value));
			valueCount++;
		}
		assertEquals(valuesFor3.size(), valueCount);
	}
}
