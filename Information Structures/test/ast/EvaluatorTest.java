/**
 *	junit testing of the evaluator class,
 *	creates an arrayList to test the string expression of
 *	the tree and checks against the double _out instance var
 *
 *	@author luke kvamme and marc d'avanzo
 *	@date 3/3/2022
 */
package ast;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class EvaluatorTest {
	// Switch to false to turn off output
	private static final boolean DEBUG = true;
	private Double _out;

	private final ArrayList<String> _in = new ArrayList<String>();
	

	private void addTest(String in, Double out)
	{
		_in.clear();
		_in.add(in);
		_out = out;
	}
	
	/**
	 * The main execution method:
	 * 
	 *    Given a set of <input, output> pairs stored within the list _in and the expected answer _out,
	 *    execute the creation and unparsing of the expression.
     *
	 *    A test will pass if the input string is evaluated to be equal (==) to the _out double.
	 */
	private void run()
	{
		//
		// Execute tree building and calculating for all input Strings
		// Compare to output doubles (assert)
		//
		for (String in : _in)
		{
			if (DEBUG) System.out.println("In:  |" + in + "|");

			// BUILD
			ASTnode tree = ASTbuilder.build(in);

			// UNPARSE
			Evaluator pfu = new Evaluator(tree);
			double eval = pfu.calc();
			

			// COMPARE
			if (DEBUG) System.out.println("Out: |" + eval + "|");
			
			assertEquals(_out, eval);
		}
	}
	
	private void constructBinaryInOutPairs()
	{
		// Start fresh
		_in.clear();
		

		//
		// Basic binary operations (integers)
		//
		String in_out = "5 3 +";
		addTest(in_out, 8.0);
		run();
	
		
		in_out = "5 3 -";
		addTest(in_out, 2.0);
		run();
		

		in_out = "5 3 *";
		addTest(in_out, 15.0);
		run();		
		
		in_out = "6 3 /";
		addTest(in_out, 2.0);
		run();
		
		in_out = "5 3 **";
		addTest(in_out, 125.0);
		run();

		
		//
		// Basic binary operations (reals)
		//
		in_out = "5.0 3.0 +";
		addTest(in_out, 8.0);
		run();
		
		in_out = "5 3 -";
		addTest(in_out, 2.0);
		run();

		in_out = "5 3 *";
		addTest(in_out, 15.0);
		run();	
		
		in_out = "6 3 /";
		addTest(in_out, 2.0);
		run();
		
		in_out = "5 3 **";
		addTest(in_out, 125.0);
		run();
		
	}
	
	@Test
	void binary_test()
	{
		if (DEBUG) System.out.println("Binary Tests");
		constructBinaryInOutPairs();
	}

	private void constructUnaryInOutPairs()
	{
		// Start fresh
		_in.clear();

		//
		// Basic unary operations (integers)
		//
		String in_out = "+3";
		addTest(in_out, 3.0);
		run();
		
		in_out = "-3";
		addTest(in_out, -3.0);
		run();

		in_out = "5 A";
		addTest(in_out, 5.0);
		run();	
		
		in_out = "16 A";
		addTest(in_out, 16.0);
		run();

		//
		// Basic unary operations (reals)
		//
		in_out = "+3";
		addTest(in_out, 3.0);
		run();
		
		in_out = "-3";
		addTest(in_out, -3.0);
		run();

		in_out = "5 A";
		addTest(in_out, 5.0);
		run();	
		
		in_out = "16 A";
		addTest(in_out, 16.0);
		run();
		
		//
		// Combined unary operations (not in expressions)
		//
		in_out = "+-3.0";
		addTest(in_out, -3.0);
		run();
		
		in_out = "-+3.0";
		addTest(in_out, -3.0);
		run();

		in_out = "++3.0";
		addTest(in_out, 3.0);
		run();

		in_out = "--3.0";
		addTest(in_out, 3.0);
		run();

		in_out = "+-++-++-++-++-++-+2";
		addTest(in_out, 2.0);
		run();
		
		in_out = "+-+5.0 A";
		addTest(in_out, 5.0);
		run();	
		
		in_out = "--16.0 S";
		addTest(in_out, 4.0);
		run();
	}
	
	@Test
	void unary_test()
	{
		if (DEBUG) System.out.println("Unary Tests");
		constructUnaryInOutPairs();
		run();
	}
	
	private void constructComplexInOutPairs()
	{
		// Start fresh
		_in.clear();


		String in_out = "+3 5 -";
		addTest(in_out, -2.0);
		run();
		
		in_out = "3";
		addTest(in_out, 3.0);
		run();
		
		in_out = "1 2 3 4 + - *";
		addTest(in_out, -5.0);
		run();
		
		in_out = "+3 6 - A 3 ** S";
		addTest(in_out, Math.sqrt(27));
		run();
		
		in_out = "+-+-----++++3 A A A A A A A S S S S S S";
		double d1 = Math.sqrt(Math.sqrt(Math.sqrt(Math.sqrt(Math.sqrt(Math.sqrt(3))))));
		addTest(in_out, d1);
		run();

		in_out = "1.23 123 3 -4 ** ** **";
		d1 = Math.pow(1.23, Math.pow(123, Math.pow(3, -4)));
		addTest(in_out, d1);
		run();
		
		in_out = "+-2.0 -+4 ** S";
		d1 = Math.sqrt(Math.pow(-2, -4));
		addTest(in_out, d1);
		run();
		
		in_out = "1.0 A 2.0 S 3.0 A 4.0 S -+5.0 + - * / A ----12.3 + 4 **";
		d1 = 1.0 / ( Math.sqrt(2) * 6); // we evaluated innermost to be 6
		d1 = Math.pow(d1 + 12.3, 4);
		addTest(in_out, d1);
		run();
		
		
	}
	
	@Test
	void complex_tests()
	{
		if (DEBUG) System.out.println("Complex Tests");
		constructComplexInOutPairs();
		run();
	}
}

