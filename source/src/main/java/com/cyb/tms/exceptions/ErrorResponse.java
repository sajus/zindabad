package com.cyb.tms.exceptions;

import java.util.List;

public class ErrorResponse {
	
	private int errorCode;
	private String message;
	private List<String> fieldErrors;
	
	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List<String> getFieldErrors() {
		return fieldErrors;
	}
	public void setFieldErrors(List<String> fieldErrors) {
		this.fieldErrors = fieldErrors;
	}
	
	

}
