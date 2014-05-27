package com.csms.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.log4j.Logger;

public class MD5Util {
	private static Logger logger = Logger.getLogger(MD5Util.class);
	public static String getMD5(String s) {
		if (s == null) {
			return s;
		}
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(s.getBytes());
			byte b[] = md.digest();
			int i;
			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			s = buf.toString();
		} catch (NoSuchAlgorithmException e) {
			logger.error(e.getMessage());

		}
		return s;
	}

	public static void main(String[] args) {
		logger.debug(getMD5("888888"));
	}
}
