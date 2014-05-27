package org.coco.calculator.factory;

import org.coco.calculator.Operation;
import org.coco.calculator.Operator;

public class OperationFactory {

	public static Operation createOperation(Operator operator) {
		try {
			return (Operation) Class.forName(
					"org.coco.calculator.impl.Operation" + operator.name())
					.newInstance();
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

}
