package org.coco.calculator.impl;

import org.coco.calculator.Operation;

public class OperationAdd implements Operation {

	@Override
	public double compute(double numberA, double numberB) {
		return numberA + numberB;
	}

}