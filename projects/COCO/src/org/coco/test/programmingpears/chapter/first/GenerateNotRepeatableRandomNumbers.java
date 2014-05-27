package org.coco.test.programmingpears.chapter.first;

import java.util.HashSet;
import java.util.Set;

public final class GenerateNotRepeatableRandomNumbers {

	private GenerateNotRepeatableRandomNumbers() {
	}

	public static byte[] generateRandomNumbers(int count, int range) {
		checkArgument(count, range);
		byte[] randomNumbers = new byte[range];
		int num = 0;
		while (true) {
			int index = GenerateRandomNumber.generateRandomNumbers(range);
			if (randomNumbers[index] == 0) {
				++num;
			}
			randomNumbers[index] += 1;
			if (num == count) {
				return randomNumbers;
			}
		}
	}

	public static byte[] generateRandomNumbers2(int count, int range) {
		checkArgument(count, range);
		byte[] bytes = new byte[range];
		Set<Integer> set = new HashSet<Integer>(count);
		while (true) {
			set.add(GenerateRandomNumber.generateRandomNumbers(range));
			if (set.size() == count) {
				for (int i : set) {
					bytes[i] = 1;
				}
				return bytes;
			}
		}
	}

	private static void checkArgument(int count, int range) {
		if (count <= 0 || count > range) {
			throw new IllegalArgumentException("输入参数不合法！");
		}
	}

}
