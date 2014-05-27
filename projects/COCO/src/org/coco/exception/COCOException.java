package org.coco.exception;

public class COCOException extends Exception {

	private static final long serialVersionUID = 1L;

	public COCOException(String message) {
		super(message);
	}

	public COCOException(String message, Throwable throwable) {
		super(message, throwable);
	}

	public COCOException(Throwable throwable) {
		super(throwable);
	}
}
