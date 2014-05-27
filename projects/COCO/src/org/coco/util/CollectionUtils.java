package org.coco.util;

import java.util.Collection;

public final class CollectionUtils {

	private CollectionUtils() {
		throw new Error("不允许实例化该类");
	}

	@SuppressWarnings("rawtypes")
	public static boolean isEmpty(Collection collection) {
		return collection == null || collection.isEmpty();
	}

	@SuppressWarnings("rawtypes")
	public static boolean isNotEmpty(Collection collection) {
		return !isEmpty(collection);
	}

}
