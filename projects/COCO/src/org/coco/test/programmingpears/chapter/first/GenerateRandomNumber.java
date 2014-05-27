package org.coco.test.programmingpears.chapter.first;

import java.util.Random;

public final class GenerateRandomNumber {

	private static final Random RANDOM = new Random();

	private GenerateRandomNumber() {
	}

	public static int generateRandomNumbers(int n) {
		checkArgument(n);
		return RANDOM.nextInt(n);
	}

	private static void checkArgument(int n) {
		if (n <= 0) {
			throw new IllegalArgumentException("输入参数不合法！");
		}
	}

}
