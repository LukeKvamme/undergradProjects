/**
 * 
 */
package heap;

import org.junit.jupiter.api.Test;

/**
 * @author calvin
 *
 */
class UnsortedListMinHeapTest extends MinHeapTest
{

	@Test
	void test_extractMin()
	{
		MinHeap<Integer> heap = new UnsortedListMinHeap<Integer>();

		extractMinSortedTest(heap);
		
		heap = new UnsortedListMinHeap<Integer>();
		
		extractMinShuffledTest(heap);
		
		heap = new UnsortedListMinHeap<Integer>();

		extractMinReverseTest(heap);
	}

}
