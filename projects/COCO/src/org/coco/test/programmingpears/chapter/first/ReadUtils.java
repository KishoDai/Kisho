package org.coco.test.programmingpears.chapter.first;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public final class ReadUtils {

	private ReadUtils() {
		throw new RuntimeException("can't instance this class.");
	}

	public static BufferedReader getBufferedReader(String fileName) {
		try {
			return new BufferedReader(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

}
