package com.gary.interview.shape.errors;

public class InvalidShapeException extends Exception {

	private static final long serialVersionUID = 1L;

	public InvalidShapeException(String message) {
		super(message);
	}

    public InvalidShapeException(String message, Throwable cause) {
        super(message, cause);
    }
}
