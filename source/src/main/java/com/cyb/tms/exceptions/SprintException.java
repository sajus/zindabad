package com.cyb.tms.exceptions;

import java.util.List;

@SuppressWarnings("serial")
public class SprintException extends RuntimeException {

	private List<String> errors;

	public SprintException(String message, List<String> errors) {
		super(message);
		this.errors = errors;
	}

	public List<String> getErrors() {
		return errors;
	}


}
