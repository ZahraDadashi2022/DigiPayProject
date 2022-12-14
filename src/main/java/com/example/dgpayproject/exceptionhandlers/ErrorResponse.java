package com.example.dgpayproject.exceptionhandlers;

public class ErrorResponse {

	private final int errorCode;
	private final String message;

	private ErrorResponse(int errorCode, String message) {
		this.errorCode = errorCode;
		this.message = message;
	}
	public static ErrorResponse of(int errorCode,String message){
		return new ErrorResponse(errorCode, message);
	}

	public int getErrorCode() {
		return errorCode;
	}

	public String getMessage() {
		return message;
	}
}







