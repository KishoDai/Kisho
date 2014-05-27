package org.coco.util;

public final class ArrayUtils {

	private ArrayUtils() {
		throw new Error("不允许实例化该类");
	}

	public static boolean isEmpty(byte[] objs) {
		return objs == null || objs.length == 0;
	}

	public static boolean isNotEmpty(byte[] objs) {
		return !isEmpty(objs);
	}

	public static boolean isEmpty(short[] objs) {
		return objs == null || objs.length == 0;
	}

	public static boolean isNotEmpty(short[] objs) {
		return !isEmpty(objs);
	}

	public static boolean isEmpty(int[] objs) {
		return objs == null || objs.length == 0;
	}

	public static boolean isNotEmpty(int[] objs) {
		return !isEmpty(objs);
	}

	public static boolean isEmpty(long[] objs) {
		return objs == null || objs.length == 0;
	}

	public static boolean isNotEmpty(long[] objs) {
		return !isEmpty(objs);
	}

	public static boolean isEmpty(float[] objs) {
		return objs == null || objs.length == 0;
	}

	public static boolean isNotEmpty(float[] objs) {
		return !isEmpty(objs);
	}

	public static boolean isEmpty(double[] objs) {
		return objs == null || objs.length == 0;
	}

	public static boolean isNotEmpty(double[] objs) {
		return !isEmpty(objs);
	}

	public static boolean isEmpty(Object[] objs) {
		return objs == null || objs.length == 0;
	}

	public static boolean isNotEmpty(Object[] objs) {
		return !isEmpty(objs);
	}

}
