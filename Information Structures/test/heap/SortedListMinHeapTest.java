/**
 * 
 */
package heap;

import org.junit.jupiter.api.Test;

/**
 * @author calvin
 *
 */
class SortedListMinHeapTest extends MinHeapTest
{

	@Test
	void test_extractMin()
	{
		MinHeap<Integer> heap = new SortedListMinHeap<Integer>();

		extractMinSortedTest(heap);
		
		heap = new SortedListMinHeap<Integer>();
		
		extractMinShuffledTest(heap);
		
		heap = new SortedListMinHeap<Integer>();

		extractMinReverseTest(heap);
	}

}
