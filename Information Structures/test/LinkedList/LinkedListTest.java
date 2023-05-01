package LinkedList;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;


/**
 * junit testing for the generic LinkedList<Item> class
 * @author lukekvamme
 * @date 01/27/2022
 */
class LinkedListTest
{
	private LinkedList<Integer> populateLinkedList(List<Integer> values)
	{
        LinkedList<Integer> list = new LinkedList<Integer>();

        // Push elements in reverse order
        for (int i = values.size() - 1; i >= 0; i--)
        {
        	list.push_front(values.get(i));
        }
        
        return list;
	}
	
	@Test
	void push_frontTest()
	{
        LinkedList<Integer> list = new LinkedList<Integer>();

        list.push_front(3);
        list.push_front(4);
        list.push_front(5);

        assertEquals("Size 1a", 3, list.size());
        assertEquals("Size 1b ", "5 4 3 ", list.toString());
        
        
        list = new LinkedList<Integer>();

        list.push_front(1);
        list.push_front(-1);
        list.push_front(2);
        list.push_front(-2);
        list.push_front(3);
        list.push_front(-3);

        assertEquals("Size 2a", 6, list.size());
        assertEquals("Size 2b ", "-3 3 -2 2 -1 1 ", list.toString());
        
        list.push_front(null);
        
        assertEquals(7, list.size());
        assertEquals("null -3 3 -2 2 -1 1 ", list.toString());
        
	}
	
	@Test
	void pop_frontTest()
	{
        LinkedList<Integer> list = new LinkedList<Integer>();

        list.push_front(1);
        list.push_front(2);
        list.push_front(3);

        Integer item = list.pop_front();
        
        assert list.size() == 2 : "Size not 2: " + list.size();
        assert item.equals(3) : "Item discontinuity: " + item;
        assert list.toString().equals("2 1 ") : list.toString();
        
        assertEquals("pop_front 1a", 2, list.size());
        assertEquals("pop_front 1b", (Integer)3, item);
        assertEquals("pop_front 1c", "2 1 ", list.toString());
        
        list.push_front(10);
        list.push_front(11);
        list.push_front(12);

        item = list.pop_front();

        assertEquals("pop_front 2a", 4, list.size());
        assertEquals("pop_front 2b", (Integer)12, item);
        assertEquals("pop_front 2c", "11 10 2 1 ", list.toString());
      
        item = list.pop_front();
        item = list.pop_front();

        assertEquals("pop_front 3a", 2, list.size());
        assertEquals("pop_front 3b", (Integer)10, item);
        assertEquals("pop_front 3c", "2 1 ", list.toString());

        item = list.pop_front();
        item = list.pop_front();

        assertEquals("pop_front 4a", 0, list.size());
        assertEquals("pop_front 4b", (Integer)1, item);
        assertEquals("pop_front 4c", "", list.toString());
        
        list.push_front(null);
        item = list.pop_front();
        
        assertEquals("", list.toString());
	}
	
	@Test
	void clearTest()
	{
        LinkedList<Integer> list = new LinkedList<Integer>();

        list.push_front(1);
        list.push_front(2);
        list.push_front(3);

        list.clear();
        
        assertEquals("clear 1a", 0, list.size());
        assertTrue("clear 1b", list.isEmpty());
	}
	
	@Test
	void containsTest()
	{
        assertFalse("Empty", new LinkedList<Integer>().contains(1));

        List<Integer> original = Arrays.asList(-2, 4, 8, 0);
        LinkedList<Integer> list = populateLinkedList(original);
        
        for (Integer item : original)
        {
        	
            assertTrue(list.contains(item));
        	
        }
        
        list.clear();

        for (Integer item : original)
        {
            assertFalse(list.contains(item));
     
        }
        
	}
	
	@Test
	void middleTest()
	{
        assertThrows(NoSuchElementException.class, () -> { new LinkedList<Integer>().middle(); } );

        LinkedList<Integer> list = populateLinkedList(Arrays.asList(-2, 4, 8, 0));

        assertEquals("Even Middle", (Integer)4, list.middle());
        
        list = populateLinkedList(Arrays.asList(-2, 8, 0));

        assertEquals("Odd Middle", (Integer)8, list.middle());
        
        
        LinkedList<Integer >list_2 = populateLinkedList(Arrays.asList(1));
        assertEquals((Integer)1, list_2.middle());
	}
	
	@Test
	void reversalTest()
	{
        LinkedList<Integer> list = new LinkedList<Integer>();

        list.reverse();
        assertEquals("Empty Reversal", list.toString(), "");

        list.push_front(1);
        list.push_front(2);
        list.push_front(3);
      

        int sz_before = list.size();
   
        list.reverse();
       
    
        assertEquals("Size unequal on reversal", list.size(), sz_before);
        assertEquals("Reversal Contents", list.toString(), "1 2 3 ");
        
        
        LinkedList<Integer> list_2 = new LinkedList<Integer>();
        
        list_2.push_front(3);
        list_2.push_front(2);
        list_2.push_front(1);
        
        int before_reverse = list_2.size();
        list_2.reverse();
        
        assertEquals(list_2.size(), before_reverse);
        assertEquals(list_2.toString(), "3 2 1 ");
        
        list_2.clear();
        assertEquals("", list_2.toString());
        int size = 0;
        
        for (int i = 0; i < 10; i++) {
        	list_2.push_front(i);
        	size++;
        }
        assertEquals(10, list_2.size());
        assertEquals("9 8 7 6 5 4 3 2 1 0 ", list_2.toString());
        
        list_2.reverse();
        assertEquals("0 1 2 3 4 5 6 7 8 9 ", list_2.toString());
        list_2.reverse();
        assertEquals("9 8 7 6 5 4 3 2 1 0 ", list_2.toString());
        
	}
	
	
}
