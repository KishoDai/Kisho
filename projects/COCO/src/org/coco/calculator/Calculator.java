package org.coco.calculator;

import org.coco.calculator.factory.OperationFactory;

public class Calculator {

	public static double compute(double numberA, double numberB,
			Operator operator) {
		return OperationFactory.createOperation(operator).compute(numberA, numberB);
	}

}
