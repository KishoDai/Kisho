package org.coco.util;

import net.sf.sojo.interchange.json.JsonSerializer;

public final class JsonUtils {

	private static final JsonSerializer jsonSerializer = new JsonSerializer();

	private JsonUtils() {
		throw new Error("不允许实例化该类");
	}

	public static String serialize(Object object) {
		return object == null ? null : String.valueOf(jsonSerializer
				.serialize(object));
	}

	public static Object deserialize(Object object) {
		return object == null ? null : jsonSerializer.deserialize(object);
	}

	public static <T> Object deserialize(Object object, Class<T> cls) {
		return object == null ? null : jsonSerializer.deserialize(object, cls);
	}

	public static void main(String[] args) {

	}

}
