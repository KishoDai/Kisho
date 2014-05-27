package com.csms.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

public class Utils {

	public static String rootPath;

	public static String substring(String arg0, int arg1) {
		if (StringUtils.isEmpty(arg0) || arg1 <= 0) {
			return "";
		}
		int l = arg0.getBytes().length;
		if (l <= arg1 || l == 1) {
			return arg0;
		}

		int totalCount = 0;
		int interceptLength = 0;
		int size = 0;
		char[] tempChars = arg0.toCharArray();
		for (int i = 0; i < tempChars.length; i++) {
			size = String.valueOf(tempChars[i]).getBytes().length;
			totalCount += size;
			if (totalCount == arg1) {
				interceptLength = i + 1;
				break;
			} else if (totalCount > arg1) {
				if (i == 0) {
					return "";
				} else {
					interceptLength = i;
					break;
				}
			}
		}
		return arg0.substring(0, interceptLength);
	}

	public static void moveFileList(List<File> srcfileList,
			List<File> destFileList) {
		File srcFile = null;
		File destFile = null;
		FileInputStream fis = null;
		FileOutputStream fos = null;
		byte[] bytes = new byte[1024];
		try {
			for (int i = 0; i < srcfileList.size(); i++) {
				srcFile = srcfileList.get(i);
				destFile = destFileList.get(i);
				if (!destFile.exists()) {
					destFile.createNewFile();
				}
				fis = new FileInputStream(srcFile);
				fos = new FileOutputStream(destFile);
				int length;
				while ((length = fis.read(bytes)) != -1) {
					fos.write(bytes, 0, length);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (fos != null) {
					fos.close();
				}
				if (fis != null) {
					fis.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		// return flag;
	}

	public static List<File> getDestFileList(String path,
			List<String> fileNameList) {
		String dateName = DateUtil.getCurrDate("yyyyMMddHHmmss");
		List<File> fileList = new ArrayList<File>();
		File file = null;
		String fileName = null;
		for (int i = 1; i <= fileNameList.size(); i++) {
			fileName = fileNameList.get(i - 1);
			file = new File(path + dateName + i
					+ (fileName.substring(fileName.lastIndexOf("."))));
			fileList.add(file);
		}
		return fileList;
	}

	public static void deleteFile(File file) {
		if (file.exists()) {
			file.delete();
		}
	}

	public static void deleteFile(String path, String fileName) {
		deleteFile(path + fileName);
	}

	public static void deleteFile(String pathname) {
		deleteFile(new File(pathname));
	}

}
