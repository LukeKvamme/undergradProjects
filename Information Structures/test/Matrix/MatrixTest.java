package Matrix;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

/**
 * a junit class to test the matrix class
 * @author lukekvamme
 * @date 2/2/22
 */
class MatrixTest {
	
	/**
	 * tests the matrix constructor
	 * 
	 * 
	 * creates a matrix of size 5x5 and sums
	 * all the values to check if it sets all
	 * values to 0.0
	 */
	@Test
	void testMatrixIntInt() {
		Matrix m = new Matrix(5,5);
		double sum = 0;
		
		for (int i = 0; i < m.getRows(); i++) {
			for (int j = 0; j < m.getColumns(); j++) {
				sum += m.get(i, j);
			}
		}
		assertEquals(0.0, sum);
		
	}
	
	/**
	 * tests the overloaded matrix constructor
	 * 
	 * 
	 * creates a list and a matrix of 3x3,
	 * iterates through with a for loop to test
	 * 
	 * sums all values in the matrix to determine if
	 * each value was being added correctly
	 */
	@Test
	void testMatrixListOfDoubleIntInt() {
		ArrayList<Double> list = new ArrayList<Double>(Arrays.asList(1.0,2.0,3.0,4.0,5.0,6.0,7.0,8.0,9.0));
		Double sum = 0.0;
		Matrix m = new Matrix(list,3,3);
		
		for (int i = 0; i < m.getRows(); i++) {
			for (int j = 0; j < m.getColumns(); j++) {
				
				sum += m.get(i, j);
			}
		}
		
		assertEquals(45.0, sum);
		
		list.clear();
		sum = 0.0;
		assertEquals(0.0, sum);	
	}
	
	/**
	 * tests the create method
	 * 
	 * 
	 * creates a matrix of 5x5 dimensions with values between 0 and 1
	 * 
	 * tests to make sure each value in the matrix is between 0 and 1
	 * by iterating with for loops and using 'get' on each place
	 */
	@Test
	void testCreate() {
		Matrix m = Matrix.create(5,5);

		for (int i = 0; i < m.getRows(); i++) {
			for (int j = 0; j < m.getColumns(); j++) {	
				assertTrue(0.0 < m.get(i, j) && m.get(i, j) < 1.0);
			}
		}
	}
	
	/**
	 * tests the get method
	 * 
	 * 
	 * creates a list and a matrix of 3x3,
	 * iterates through with a for loop to test
	 * 
	 * tests whether the value at the index of the list
	 * used to create the matrix is the same as the value
	 * returned by the 'get' method
	 */
	@Test
	void testGet() {
		ArrayList<Double> list = new ArrayList<Double>(Arrays.asList(1.0,2.0,3.0,4.0,5.0,6.0,7.0,8.0,9.0));
		int index = 0;
		Matrix m = new Matrix(list,3,3);
		
		for (int i = 0; i < m.getRows(); i++) {
			for (int j = 0; j < m.getColumns(); j++) {
				
				assertEquals(list.get(index), m.get(i,j));
				index++;
				
			}
		}
	}
	
	/**
	 * tests the set method
	 * 
	 * 
	 * creates a new list of size nine and a matrix of size 3x3
	 * 
	 * goes through entire list and sets all values to 0.0,
	 * checks with sum that all values equal 0.0 at the end
	 */
	@Test
	void testSet() {
		ArrayList<Double> list = new ArrayList<Double>(Arrays.asList(1.0,2.0,3.0,4.0,5.0,6.0,7.0,8.0,9.0));
		Matrix m = new Matrix(list,3,3);
		double dub = 0.0;
		double sum = 0.0;
		
		for (int i = 0; i < m.getRows(); i++) {
			for (int j = 0; j < m.getColumns(); j++) {
				m.set(dub, i, j);
				sum += m.get(i, j);
			}
		}
		
		assertEquals(0.0, sum);

	}
	
	/**
	 * tests the getRows method
	 * 
	 * 
	 * creates a matrix and determines if the int
	 * returned by getRows is correct
	 * 
	 * creates another matrix with another row
	 * value and retests
	 */
	@Test
	void testGetRows() {
		ArrayList<Double> list = new ArrayList<Double>(Arrays.asList(1.0,2.0,3.0,4.0,5.0,6.0,7.0,8.0,9.0));
		Matrix m = new Matrix(list,3,3);
		
		assertEquals(3, m.getRows());
		
		
		Matrix n = new Matrix(list,1,3);
		
		assertEquals(1, n.getRows());
		
	}
	
	/**
	 * tests the getColumnss method
	 * 
	 * creates a matrix and determines if the int
	 * returned by getColumnss is correct
	 * 
	 * creates another matrix with another column
	 * value and retests
	 */
	@Test
	void testGetColumnss() {
		ArrayList<Double> list = new ArrayList<Double>(Arrays.asList(1.0,2.0,3.0,4.0,5.0,6.0,7.0,8.0,9.0));
		Matrix m = new Matrix(list,3,3);
		
		assertEquals(3, m.getColumns());
		
		
		Matrix n = new Matrix(list,3,1);
		
		assertEquals(1, n.getColumns());
	}
	
	/**
	 * tests the toString method
	 * 
	 * creates a list of six numbers and uses it to create
	 * an array of size 3x3
	 * 
	 * turns the matrix into an array and tests whether it turned into a string correctly
	 * 
	 * the list is purposefully 3 numbers short in order to test whether it turns the default
	 * 0.0's in the matrix into a string properly
	 */
	@Test
	void testToString() {
		ArrayList<Double> list = new ArrayList<Double>(Arrays.asList(1.0,2.0,3.0,4.0,5.0,6.0));
		Matrix m = new Matrix(list,3,3);
		
		assertEquals("1.0  2.0  3.0  \n" + "4.0  5.0  6.0  \n" + "0.0  0.0  0.0  \n", m.toString());
	}
	
	/**
	 * tests the plus method
	 * 
	 * 
	 * creates two lists of size 9 and two matrices of size 3x3
	 * 
	 * adds them together and tests with the toString
	 * 
	 * then creates a third matrix of size 2x3 to test the 
	 * thrown error RuntimeException
	 */
	@Test
	void testPlus() {
		ArrayList<Double> list_one = new ArrayList<Double>(Arrays.asList(1.0,2.0,3.0,4.0,5.0,6.0,7.0,8.0,9.0));
		ArrayList<Double> list_two = new ArrayList<Double>(Arrays.asList(1.0,2.0,3.0,4.0,5.0,6.0,7.0,8.0,9.0));
		Matrix m = new Matrix(list_one, 3, 3);
		Matrix that = new Matrix(list_two, 3, 3);
		Matrix summed = m.plus(that);
		
		assertEquals(summed.toString(), "2.0  4.0  6.0  \n" + "8.0  10.0  12.0  \n" + "14.0  16.0  18.0  \n");
		
		
		Matrix that_2 = new Matrix(list_two, 2, 3);
		
		assertThrows(RuntimeException.class, () -> {m.plus(that_2);});
		
		
		ArrayList<Double> list_three = new ArrayList<Double>(Arrays.asList(1.0,2.0,3.0,4.0,5.0,6.0,7.0,8.0,9.0));
		ArrayList<Double> list_four = new ArrayList<Double>(Arrays.asList(1.0,2.0,3.0,4.0,5.0,6.0,7.0,8.0,9.0));
		Matrix m2 = new Matrix(list_one, 3, 3);
		Matrix that2 = new Matrix(list_two, 3, 3);
		Matrix summed2 = that2.plus(m2);
		
		assertEquals(summed2.toString(), "2.0  4.0  6.0  \n" + "8.0  10.0  12.0  \n" + "14.0  16.0  18.0  \n");
	}
	
	
	/**
	 * tests the times method
	 * 
	 * 
	 * creates four lists and four matrices
	 * 
	 * 
	 * the first set of lists and matrices are from the slides,
	 * one list being a length of six and the other being three
	 * 
	 * creates a matrix of size 2x3 and another of size 3x1
	 * multiplies them together, using the toString method to 
	 * see it in plaintext and comparing it against the example
	 * in the powerpoint slides
	 * 
	 * 
	 * then catches throws RuntimeException by testing multiplying
	 * a matrix of 2x1 and a matrix of 2x3
	 * 
	 * 
	 * creates two more lists of size six as well as two more matrices
	 * of size 2x3 and 3x2 to test multiple columns and rows being
	 * multiplied. uses toString to check
	 */
	@Test
	void testTimes() {
		
		ArrayList<Double> list_one = new ArrayList<Double>(Arrays.asList(4.0,5.0,8.0,-1.0,4.0,6.0));
		ArrayList<Double> list_two = new ArrayList<Double>(Arrays.asList(2.0,6.0,9.0));
		Matrix m = new Matrix(list_one, 2, 3);
		Matrix o = new Matrix(list_two, 3, 1);
		
		Matrix mult = m.times(o);
		
		assertEquals("110.0  \n" + "76.0  \n", mult.toString());
		
		assertThrows(RuntimeException.class, () -> {mult.times(o);});
		
		
		ArrayList<Double> list_three = new ArrayList<Double>(Arrays.asList(4.0,5.0,8.0,-1.0,4.0,6.0));
		ArrayList<Double> list_four = new ArrayList<Double>(Arrays.asList(2.0,6.0,9.0, 9.0, 6.0, 2.0));
		Matrix n = new Matrix(list_three, 2, 3);
		Matrix p = new Matrix(list_four, 3, 2);
		
		Matrix multi = n.times(p);
		
		assertEquals("101.0  85.0  \n" + "70.0  42.0  \n", multi.toString());
	}

	
	
}
