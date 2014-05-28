package org.coco.test.programmingpears.chapter.first;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public final class WriteUtils {

	private WriteUtils() {
		throw new RuntimeException("can't instance this class.");
	}

	public static BufferedWriter getBufferedWriter(String fileName) {
		return getBufferedWriter(fileName, false);
	}

	public static BufferedWriter getBufferedWriter(String fileName,
			boolean append) {
		try {
			return new BufferedWriter(new FileWriter(fileName, append));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static PrintWriter getPrintWriter(String fileName) {
		try {
			return new PrintWriter(new File(fileName));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
