/**
 * 
 */
package heap;

import org.junit.jupiter.api.Test;

/**
 * @author lkvamme
 *
 */
class ClassicMinHeapTest extends MinHeapTest
{

	@Test
	void test_extractMin()
	{
		MinHeap<Integer> heap = new ClassicMinHeap<Integer>();

		extractMinSortedTest(heap);
		
		heap = new ClassicMinHeap<Integer>();
		
		extractMinShuffledTest(heap);
		
		heap = new ClassicMinHeap<Integer>();

		extractMinReverseTest(heap);
	}

}
