package org.coco.test.programmingpears.chapter.first;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public final class WriteDataToFile {

	private WriteDataToFile() {
	}

	public static void writeByteToFile(byte[] bytes, String fileName, boolean order) {
		PrintWriter pw = null;
		try {
			File file = new File(fileName);
			if (!(!file.exists() ? file.createNewFile() : true)) {
				throw new RuntimeException("文件不存在，创建文件不成功!");
			}
			pw = new PrintWriter(file);
			if (order) {
				for (int i = 0; i < bytes.length; i++) {
					writeByte(pw, i, bytes);
				}
			} else {
				for (int i = bytes.length - 1; i >= 0; i--) {
					writeByte(pw, i, bytes);
				}
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			pw.close();
		}
	}

	private static void writeByte(PrintWriter pw, int i, byte[] bytes) {
		if (bytes[i] >= 1) {
			pw.write(i + "\n");
		}
	}

}
