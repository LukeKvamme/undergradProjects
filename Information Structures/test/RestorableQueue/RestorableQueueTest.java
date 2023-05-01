package RestorableQueue;
import static org.junit.jupiter.api.Assertions.*;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.Test;

/**
 * Tests the RestorableQueue class
 * @author lukekvamme
 * @date 02/15/2022
 *
 */
class RestorableQueueTest {

	/**
	 * tests the restorable queue constructor
	 * 
	 * 
	 * creates a restorable queue, checks if it initialized
	 * as an empty queue with a size of 0
	 */
	@Test
	void testRestorableQueue() {
		RestorableQueue<Integer> q = new RestorableQueue<Integer>();
		assertTrue(q.isEmpty());
		assertEquals(0, q.size());
	}

	/**
	 * tests the size method of restorable queue
	 * 
	 * 
	 * creates a queue, checks if the size is 0.
	 * adds values 0-19 to the queue, checking size as
	 * they are added. Then dequeues back to empty
	 */
	@Test
	void testSize() {
		RestorableQueue<Integer> q = new RestorableQueue<Integer>();
		assertEquals(0, q.size());
		
		for (int i = 0; i < 20; i++) {
			q.enqueue(i);
			assertEquals(i + 1, q.size());
		}
		
		assertEquals(20, q.size());
		
		for (int i = 20; i > 0; i--) {
			q.dequeue();
			assertEquals(i - 1, q.size());
		}
		assertTrue(q.isEmpty());
	}

	/**
	 * tests the isEmpty method of restorable queue
	 * 
	 * 
	 * creates a queue, checks if it is empty.
	 * then enqueues values 0-19, checks for size increase
	 * and if the queue is not empty.
	 * 
	 * then dequeues all values, checks if the queue is empty
	 */
	@Test
	void testIsEmpty() {
		RestorableQueue<Integer> q = new RestorableQueue<Integer>();
		assertTrue(q.isEmpty());
		
		for (int i = 0; i < 20; i++) {
			q.enqueue(i);
			assertEquals(i + 1, q.size());
			assertFalse(q.isEmpty());
		}
		
		assertFalse(q.isEmpty());
		assertEquals(20, q.size());
		
		
		for (int i = 20; i > 0; i--) {
			q.dequeue();
			assertEquals(i - 1, q.size());
		}
		assertTrue(q.isEmpty());
	}

	/**
	 * tests the clear method of the restorable queue
	 * 
	 * 
	 * creates a new queue, checks if it is empty.
	 * adds values 0-19 to the queue, checks that the
	 * size is 20. then clears and checks if queue is
	 * now empty
	 */
	@Test
	void testClear() {
		RestorableQueue<Integer> q = new RestorableQueue<Integer>();
		assertTrue(q.isEmpty());
		
		for (int i = 0; i < 20; i++) {
			q.enqueue(i);
			assertEquals(i + 1, q.size());
		}
		
		assertEquals(20, q.size());
		
		q.clear();
		assertTrue(q.isEmpty());
	}
	
	/**
	 * tests the enqueue method of restorable queue
	 * 
	 * 
	 * creates a queue, checks if it is empty.
	 * adds values 0-19 to the queue, checks
	 * the size and then checks if the queue 
	 * is actually 0-19 using toArray()
	 */
	@Test
	void testEnqueue() {
		RestorableQueue<Integer> q = new RestorableQueue<Integer>();
		assertTrue(q.isEmpty());
		
		for (int i = 0; i < 20; i++) {
			q.enqueue(i);
			assertEquals(i + 1, q.size());
		}
		
		assertEquals(20, q.size());
		assertArrayEquals(new Object[] {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19}, q.toArray());
		
	}
	
	/**
	 * tests the dequeue method of restorable queue
	 * 
	 * 
	 * creates a queue, checks if it is empty.
	 * adds values 0-19 to the queue, then dequeues
	 * every value, checking size along the way.
	 * checks if queue is empty at the end.
	 * 
	 * then checks the thrown NoSuchElementException
	 * by trying to dequeue the empty queue again
	 */
	@Test
	void testDequeue() {
		RestorableQueue<Integer> q = new RestorableQueue<Integer>();
		assertTrue(q.isEmpty());
		
		for (int i = 0; i < 20; i++) {
			q.enqueue(i);
			assertEquals(i + 1, q.size());
		}
		
		assertEquals(20, q.size());
		
		for (int i = 20; i > 0; i--) {
			q.dequeue();
			assertEquals(i - 1, q.size());
		}
		assertTrue(q.isEmpty());
		
		assertThrows(NoSuchElementException.class, () -> {q.dequeue();});
	}
	
	/**
	 * tests the peek method of the restorable queue
	 * 
	 * 
	 * creates a queue, checks if it is empty.
	 * peeks the empty queue to make sure the value is null,
	 * then adds values 0-19 to the queue and peeks in each loop
	 * to make sure that it is 0 every time
	 */
	@Test
	void testPeek() {
		RestorableQueue<Integer> q = new RestorableQueue<Integer>();
		assertTrue(q.isEmpty());
		assertEquals(null, q.peek());
		
		for (int i = 0; i < 20; i++) {
			q.enqueue(i);
			assertEquals(0, q.peek());
		}
		assertEquals(0, q.peek());
		assertEquals(20, q.size());
		
	}
	
	/**
	 * tests the contains method of the restorable queue
	 * 
	 * 
	 * creates a queue, checks if it is empty.
	 * then checks if 12 is in the queue.
	 * adds values 0-19, checking to see
	 * if it contains the value it just added.
	 * 
	 * checks to see if the value 100 is in the queue.
	 * 
	 * removes each value from the queue, checking to see
	 * if the value just removed is in the queue
	 */
	@Test
	void testContains() {
		RestorableQueue<Integer> q = new RestorableQueue<Integer>();
		assertTrue(q.isEmpty());
		assertFalse(q.contains(12));
		
		for (int i = 0; i < 20; i++) {
			q.enqueue(i);
			assertTrue(q.contains(i));
		}
		
		assertFalse(q.contains(100));
		
		for (int i = 0; i < 20; i++) {
			q.dequeue();
			assertFalse(q.contains(i));
		}
		
	}

	/**
	 * tests the toArray() method of the restorable queue
	 * 
	 * 
	 * creates a queue, checks if it is empty.
	 * adds values 0-19 to the queue.
	 * then checks toArray() by manually checking it
	 * against a new Object[] array with the values
	 * 0-19.
	 * 
	 * dequeues 5 times, and checks again with the values
	 * 5-19.
	 */
	@Test
	void testToArray() {
		RestorableQueue<Integer> q = new RestorableQueue<Integer>();
		assertTrue(q.isEmpty());
		
		for (int i = 0; i < 20; i++) {
			q.enqueue(i);
		}
		assertArrayEquals(new Object[] {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19}, q.toArray());
		
		for (int i = 0; i< 5; i++) {
			q.dequeue();
		}
		assertArrayEquals(new Object[] {5,6,7,8,9,10,11,12,13,14,15,16,17,18,19}, q.toArray());
		
	}

	/**
	 * tests the saveState() method of the restorable queue
	 * 
	 * 
	 * creates a queue, checks if it is empty.
	 * asserts false that you can't revertState().
	 * 
	 * adds values 1-3, saves it, checks if the Memento stack
	 * is empty, then messes up the queue.
	 * reverts the queue back to [1,2,3] and clears
	 * 
	 * adds values 4-6, saves it, then messes up the queue.
	 * reverts the queue back to [4,5,6] and clears
	 * 
	 * adds values 7-9, saves it, then messes up the queue.
	 * reverts the queue back to [7,8,9]
	 */
	@Test
	void testSaveState() {
		RestorableQueue<Integer> q = new RestorableQueue<Integer>();
		assertTrue(q.isEmpty());
		assertFalse(q.revertState());
		
		for (int i = 1; i < 4; i++) {
			q.enqueue(i);
		}
		q.saveState();
		assertFalse(q._reversions.isEmpty());
		
		q.enqueue(12);
		q.enqueue(14);
		
		q.revertState();
		assertArrayEquals(new Object[] {1,2,3}, q.toArray());
		q.clear();
		
		
		for (int i = 4; i < 7; i++) {
			q.enqueue(i);
		}
		q.saveState();
		q.dequeue();
		q.enqueue(27);
		
		q.revertState();
		assertArrayEquals(new Object[] {4,5,6}, q.toArray());
		q.clear();
		
		
		
		for (int i = 7; i < 10; i++) {
			q.enqueue(i);
		}
		q.saveState();
		q.enqueue(12);
		q.dequeue();
		
		q.revertState();
		assertArrayEquals(new Object[] {7,8,9}, q.toArray());
		
		
	}

	/**
	 * tests the revertState() method of the restorable queue
	 * 
	 * 
	 * creates a queue, checks if it is empty.
	 * enqueues values 1-3 and saves it, then checks if 
	 * the Memento stack is not empty. then clears.
	 * 
	 * enqueues values 4-6 and saves it, then checks if 
	 * the Memento stack is not empty. then clears.
	 * 
	 * enqueues values 7-9 and saves it, then checks if 
	 * the Memento stack is not empty. then messes up the queue
	 * 
	 * 
	 * reverts the queue back to the state at the top of the stack,
	 * chekcs if it is [7,8,9]. then reverts to check that 
	 * the next saved state is [4,5,6]. then reverts to check
	 * that the next saved state is [1,2,3]. checks if the 
	 * Memento stack is empty and clears the queue.
	 * 
	 * 
	 * enqueues values 0-19 to the queue and saves it.
	 * checks that the Memento stack is not empty,
	 * and messes up the queue. then reverts back to 
	 * the saved state and checks that the queue
	 * is of values 0-19.
	 */
	@Test
	void testRevertState() {
		RestorableQueue<Integer> q = new RestorableQueue<Integer>();
		assertTrue(q.isEmpty());
		
		
		for (int i = 1; i < 4; i++) {
			q.enqueue(i);
		}
		q.saveState();
		assertFalse(q._reversions.isEmpty());
		
		
		q.clear();
		for (int i = 4; i < 7; i++) {
			q.enqueue(i);
		}
		q.saveState();
		assertFalse(q._reversions.isEmpty());
		
		
		q.clear();
		for (int i = 7; i < 10; i++) {
			q.enqueue(i);
		}
		q.saveState();
		assertFalse(q._reversions.isEmpty());
		
		q.dequeue();
		q.dequeue();
		q.enqueue(90);
		q.enqueue(99);

		
		q.revertState();
		assertArrayEquals(new Object[] {7,8,9}, q.toArray());
		
		q.revertState();
		assertArrayEquals(new Object[] {4,5,6}, q.toArray());
		
		q.revertState();
		assertArrayEquals(new Object[] {1,2,3}, q.toArray());
		assertTrue(q._reversions.isEmpty());
		q.clear();
		
		
		for (int i = 0; i < 20; i++) {
			q.enqueue(i);
		}
		q.saveState();
		assertFalse(q._reversions.isEmpty());
		
		q.dequeue();
		q.dequeue();
		q.enqueue(90);
		q.enqueue(100);
		q.enqueue(18);
		
		q.revertState();
		assertArrayEquals(new Object[] {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19}, q.toArray());
		
	}
	
}
