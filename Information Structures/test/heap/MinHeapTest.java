/**
 * 
 */
package heap;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

/**
 * @author calvin
 *
 */
class MinHeapTest
{
	//
	// The heaps should have the same operations; test
    // with the same methodology
	//
	public void extractMinReverseTest(MinHeap<Integer> heap)
	{
		List<Integer> values = new ArrayList<Integer>();
		List<Double> keys = new ArrayList<Double>();

		final int LOW = 0;    // included value
		final int HIGH = 100; // included value

		// POPULATE
		for (int i = HIGH; i >= LOW; i--)
		{
			values.add(i);
			keys.add((double)i);
		}
		
		heap.build(values, keys);
		
		Collections.sort(keys);
		
		// VALIDATE
		validateExtractMinWithOrderedKeys(heap, keys);
	}

	//
	// The heaps should have the same operations; test
	// with the same methodology
	//
	public void extractMinSortedTest(MinHeap<Integer> heap)
	{
		List<Integer> values = new ArrayList<Integer>();
		List<Double> keys = new ArrayList<Double>();

		final int LOW = 100;    // included value
		final int HIGH = 200; // included value

		// POPULATE
		for (int i = LOW; i <= HIGH; i++)
		{

			values.add(i);
			keys.add((double)i);
		}
		//  System.out.println(values);
		//  System.out.println(keys);
		
		heap.build(values, keys);

		// VALIDATE
		validateExtractMinWithOrderedKeys(heap, keys);
	}

	public void extractMinShuffledTest(MinHeap<Integer> heap)
	{
		List<Integer> values = new ArrayList<Integer>();
		List<Double> orderedkeys = new ArrayList<Double>();
		
		final int LOW = 200;    // included value
		final int HIGH = 300; // included value

		// POPULATE
		for (int i = LOW; i <= HIGH; i++)
		{
			values.add(i);
			orderedkeys.add((double)i);
		}

		Collections.shuffle(values);

		List<Double> shuffledkeys = new ArrayList<Double>();
		values.forEach(v -> shuffledkeys.add((double)v)); // Maintain ordering
		
		heap.build(values, shuffledkeys);

		// VALIDATE
		validateExtractMinWithOrderedKeys(heap, orderedkeys);
	}
	
	//
	// The heaps should have the same operations; test
    // with the same methodology
	//
	public void validateExtractMinWithOrderedKeys(MinHeap<Integer> heap, List<Double> keys)
	{
		assertEquals(keys.size(), heap.size());
		
		
		// EXTRACT_MIN
		for (Double key : keys)
		{
			HeapNode<Integer> minNode = heap.extractMin();
			System.out.println(minNode + " min node ");
			
			// System.out.println(heap.size() + " heap " + keys.size() + " keys");
		    //  System.out.println("minNode: " + minNode + " key: " + key);
		    
			
			if (!Numeric.neighborhoodEquals(minNode._key, key))
			{
				System.err.println("Comparing " + minNode._key + " " + key);
			}
			assertTrue(Numeric.neighborhoodEquals(minNode._key, key));
		}
	}	
}
