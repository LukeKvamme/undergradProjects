package PythonList;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
/**
 * Tests PythonList class
 * @author lukekvamme
 * @date 01/20/2022
 *
 */

class PythonListTest {
	
	/**
	 * tests add method from PythonList class
	 * creates PythonList list_pos of Integers [1,2,3,null]
	 * and assertEquals to check index and int and null value
	 * 
	 * creates PythonList list_neg of Integers [1,2,3,4]
	 * and uses negative indices to add inside of the array
	 * 
	 * 
	 * creates PythonList list_str of Strings ["", "a", "bc"] to test genericness
	 * and assertsEquals to check index and Str
	 * and to check empty str
	 * 
	 */

	@Test
	void testAdd() {
			PythonList<Integer> list_pos = new PythonList<Integer>();
				
			
			for (int i = 0; i < 10; i++) {
				list_pos.add(i, i + 1);
			}
				
			for (int i = 0; i < 10; i++) {
			assertEquals(i + 1, list_pos.get(i));
			}
			
			list_pos.add(10, null);
			assertNull(list_pos.get(10));
			
			
			/*
			list_pos.add(0, 1);
			list_pos.add(1, 2);
			list_pos.add(2, 3);
			list_pos.add(3, null);
			
			assertEquals(1, list_pos.get(0));
			assertEquals(2, list_pos.get(1));
			assertEquals(3, list_pos.get(2));
			assertEquals(null, list_pos.get(3));
			*/
			
			
			PythonList<Integer> list_neg = new PythonList<Integer>(Arrays.asList(1,2,3,4));
			list_neg.add(-2, 8);
			assertEquals(8, list_neg.get(-3));
			
			
			
			PythonList<String> list_str = new PythonList<String>();
			list_str.add(0, "");
			list_str.add(1, "a");
			list_str.add(2, "bc");
			
			assertEquals("",list_str.get(0));
			assertEquals("a", list_str.get(1));
			assertEquals("bc", list_str.get(2));
			
	}
	/**
	 * tests get method from PythonList class
	 * creates PythonList list_int of Integers [1,2,3,4,5]
	 * and tests using assertEquals with positive indices
	 * then tests using assertEquals with negative indices
	 */
	@Test
	void testGet() {
		PythonList<Integer> list_int = new PythonList<Integer>(Arrays.asList(1,2,3,4,5));
		
		assertEquals(1, list_int.get(0));
		assertEquals(2, list_int.get(1));
		assertEquals(3, list_int.get(2));
		assertEquals(4, list_int.get(3));
		assertEquals(5, list_int.get(4));
		
		assertEquals(5, list_int.get(-1));
		assertEquals(4, list_int.get(-2));
		assertEquals(3, list_int.get(-3));
		assertEquals(2, list_int.get(-4));
		assertEquals(1, list_int.get(-5));
	}
/**
 * tests the remove method from PythonList class
 * creates PythonList list_posof Integers [1,2,3,4,5]
 * then removes from list using positive indices
 * 
 * then creates PythonList list_neg of Integers [1,2,3,4,5]
 * then removes from list using negative indices
 */
	@Test
	void testRemove() {
		PythonList<Integer> list_pos = new PythonList<Integer>(Arrays.asList(1,2,3,4,5));
		
		assertEquals(1, list_pos.remove(0));
		assertEquals(2, list_pos.remove(0));
		assertEquals(5, list_pos.remove(2));
		assertEquals(4, list_pos.remove(1));
		assertEquals(3, list_pos.remove(0));
		
		
		PythonList<Integer> list_neg = new PythonList<Integer>(Arrays.asList(1,2,3,4,5));
		
		assertEquals(5, list_neg.remove(-1));
		assertEquals(2, list_neg.remove(-3));
		assertEquals(1, list_neg.remove(-3));
		assertEquals(3, list_neg.remove(-2));
		assertEquals(4, list_neg.remove(-1));
		
	}
/**
 * tests the set method from the PythonList class
 * creates PythonList list_pos of Integers [1,2,3,4,5]
 * then sets first, middle, last Integer to its negative using positive indices
 * 
 * then creates PythonList list_neg of Integers [1,2,3,4,5]
 * and repeats using negative indices
 */
	@Test
	void testSet() {
		PythonList<Integer> list_pos = new PythonList<Integer>(Arrays.asList(1,2,3,4,5));
		
		assertEquals(1, list_pos.set(0, -1));
		assertEquals(3, list_pos.set(2, -3));
		assertEquals(5, list_pos.set(4, -5));
		
		assertEquals(-1, list_pos.get(0));
		assertEquals(-3, list_pos.get(2));
		assertEquals(-5, list_pos.get(4));
		
		
		PythonList<Integer> list_neg = new PythonList<Integer>(Arrays.asList(1,2,3,4,5));
		
		assertEquals(1, list_neg.set(-5, -1));
		assertEquals(3, list_neg.set(-3, -3));
		assertEquals(5, list_neg.set(-1, -5));
		
		assertEquals(-1, list_neg.get(-5));
		assertEquals(-3, list_neg.get(-3));
		assertEquals(-5, list_neg.get(-1));
		
	}
	
/**
 * tests the sublist method from the PythonList class
 * creates PythonList list_pos of Integers [1,2,3,4,5,6,7,8,9,10]
 * then uses positive indices to create a sublist of [4,5,6,7]
 * 
 * creates PythonList list_neg of Integers [1,2,3,4,5,6,7,8,9,10]
 * then uses negative indices to create a sublist of [4,5,6,7]
 * 
 * catches thrown error IllegalArgumentException using invalid
 * positive and negative indices
 */
	@Test
	void testSubList() {
		PythonList<Integer> list_pos = new PythonList<Integer>(Arrays.asList(1,2,3,4,5,6,7,8,9,10));
		PythonList<Integer> sub_pos = list_pos.subList(3, 7);
		
		assertEquals(4, sub_pos.get(0));
		assertEquals(5, sub_pos.get(1));
		assertEquals(6, sub_pos.get(2));
		assertEquals(7, sub_pos.get(3));
		
		assertThrows(IllegalArgumentException.class, () -> {list_pos.subList(7, 3);});
		
		
		
		PythonList<Integer> list_neg = new PythonList<Integer>(Arrays.asList(1,2,3,4,5,6,7,8,9,10));
		PythonList<Integer> sub_neg = list_neg.subList(-8, -3);
		
		assertEquals(4, sub_neg.get(-4));
		assertEquals(5, sub_neg.get(-3));
		assertEquals(6, sub_neg.get(-2));
		assertEquals(7, sub_neg.get(-1));
		
		assertThrows(IllegalArgumentException.class, () -> {list_neg.subList(-3, -8);});
		
		
		
	}
	
}
