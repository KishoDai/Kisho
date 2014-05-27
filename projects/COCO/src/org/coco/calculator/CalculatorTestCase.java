package org.coco.calculator;

import junit.framework.TestCase;

public class CalculatorTestCase extends TestCase {

	public void testAdd() {
		assertTrue(Calculator.compute(2, 3, Operator.Add) == 5.0);
	}

	public void testSub() {
		assertTrue(Calculator.compute(2, 3, Operator.Sub) == -1.0);
	}

	public void testMul() {
		assertTrue(Calculator.compute(2, 3, Operator.Mul) == 6.0);
	}

	public void testDiv() {
		assertTrue(Calculator.compute(4, 2, Operator.Div) == 2.0);
	}

}
