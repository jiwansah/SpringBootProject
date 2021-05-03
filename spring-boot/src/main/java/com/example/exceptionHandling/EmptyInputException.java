package com.example.exceptionHandling;

// We can also extends RuntimeException or Exception
public class EmptyInputException extends RuntimeException {
	
	
	private static final long serialVersionUID = 1L;
	private int code;
	
	public int getCode() {
		return code;
	}
	public EmptyInputException() {
		super();
	}
	public EmptyInputException(String msg) {
		super(msg);
	}
	public EmptyInputException(int code, String msg) {
		super(msg);
		this.code = code;
	}
	public EmptyInputException(String message, Throwable cause) {
        super(message, cause);
    }
	public EmptyInputException(Throwable cause) {
        super(cause);
    }
}
