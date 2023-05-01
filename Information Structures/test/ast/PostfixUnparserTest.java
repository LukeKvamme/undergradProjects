/**
 * Unit tests for parsing an expression into an expression tree of ASTnode objects.
 * Tests include:
 *    Unary Postfix Operators
 *      A, S
 *    Binary Operators
 *      +, -, *, / **
 *    Unary Prefix
 *      +, -
 *    A set of complex tests using all possible operators.
 * 
 * @author C. Alvin
 * 2/11/2021
 */
package ast;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import ast.PostfixUnparser;

class PostfixUnparserTest
{
	// Switch to false to turn off output
	private static final boolean DEBUG = true;

	private final ArrayList<String> _in = new ArrayList<String>();
	private final ArrayList<String> _out = new ArrayList<String>();

	private void addTest(String in, String out)
	{
		_in.add(in);
		_out.add(out);
	}
	
	/**
	 * The main execution method:
	 * 
	 *    Given a set of <input, output> pairs stored within the lists <_in, _out>,
	 *    execute the creation and unparsing of the expression.
     *
	 *    A test will pass if the input / output strings are equal().
	 */
	private void run()
	{
		//
		// Execute tree building and unparsing for all input Strings
		// Compare to output strings (assert)
		//
		for (String in : _in)
		{
			if (DEBUG) System.out.println("In:  |" + in + "|");

			// BUILD
			ASTnode tree = ASTbuilder.build(in);

			// UNPARSE
			PostfixUnparser pfu = new PostfixUnparser(tree);
			String unparsed = pfu.unparse();

			// COMPARE
			if (DEBUG) System.out.println("Out: |" + unparsed + "|");
			
			assertEquals(in, unparsed);
		}
	}
	
	private void constructBinaryInOutPairs()
	{
		// Start fresh
		_in.clear();
		_out.clear();

		//
		// Basic binary operations (integers)
		//
		String in_out = "5 3 +";
		addTest(in_out, in_out);
		
		in_out = "5 3 -";
		addTest(in_out, in_out);

		in_out = "5 3 *";
		addTest(in_out, in_out);		
		
		in_out = "5 3 /";
		addTest(in_out, in_out);
		
		in_out = "5 3 **";
		addTest(in_out, in_out);

		//
		// Basic binary operations (reals)
		//
		in_out = "5.0 3.0 +";
		addTest(in_out, in_out);
		
		in_out = "5.0 3.0 -";
		addTest(in_out, in_out);

		in_out = "5.0 3.0 *";
		addTest(in_out, in_out);		
		
		in_out = "5.0 3.0 /";
		addTest(in_out, in_out);
		
		in_out = "5.0 3.0 **";
		addTest(in_out, in_out);
	}
	
	@Test
	void binary_test()
	{
		if (DEBUG) System.out.println("Binary Tests");
		constructBinaryInOutPairs();
		run();
	}

	private void constructUnaryInOutPairs()
	{
		// Start fresh
		_in.clear();
		_out.clear();

		//
		// Basic unary operations (integers)
		//
		String in_out = "+3";
		addTest(in_out, in_out);
		
		in_out = "-3";
		addTest(in_out, in_out);

		in_out = "5 A";
		addTest(in_out, in_out);		
		
		in_out = "16 A";
		addTest(in_out, in_out);

		//
		// Basic unary operations (reals)
		//
		in_out = "+3.0";
		addTest(in_out, in_out);
		
		in_out = "-3.0";
		addTest(in_out, in_out);

		in_out = "5.0 A";
		addTest(in_out, in_out);		
		
		in_out = "16.0 S";
		addTest(in_out, in_out);
		
		//
		// Combined unary operations (not in expressions)
		//
		in_out = "+-3.0";
		addTest(in_out, in_out);
		
		in_out = "-+3.0";
		addTest(in_out, in_out);

		in_out = "++3.0";
		addTest(in_out, in_out);

		in_out = "--3.0";
		addTest(in_out, in_out);

		in_out = "+-++-++-++-++-++-+2";
		addTest(in_out, in_out);
		
		in_out = "+-+5.0 A";
		addTest(in_out, in_out);		
		
		in_out = "--16.0 S";
		addTest(in_out, in_out);
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
		_out.clear();

		String in_out = "+3 5 -";
		addTest(in_out, in_out);
		
		in_out = "1 2 3 4 + - *";
		addTest(in_out, in_out);
		
		in_out = "+3 5 - A 3 ** S";
		addTest(in_out, in_out);
		
		in_out = "+-+-----++++3 A A A A A A A S S S S S S";
		addTest(in_out, in_out);

		in_out = "1.23 123 3 -4 ** ** **";
		addTest(in_out, in_out);
		
		in_out = "+-2.0 -+4 ** S";
		addTest(in_out, in_out);
		
		in_out = "1.0 A 2.0 S 3.0 A 4.0 S -+5.0 + - * / A ----12.3 + 4 **";
		addTest(in_out, in_out);
	}
	
	@Test
	void complex_tests()
	{
		if (DEBUG) System.out.println("Complex Tests");
		constructComplexInOutPairs();
		run();
	}
}
