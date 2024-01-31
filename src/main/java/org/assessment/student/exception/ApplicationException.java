package org.assessment.student.exception;

import org.assessment.student.enums.ErrorCode;

public class ApplicationException extends RuntimeException {

	private final String code;
	public ApplicationException(String code, String message) {
		super(message);
		this.code = code;
	}

	public ApplicationException(ErrorCode errorCode, String message) {
		super(message);
		this.code = errorCode.getCode();
	}

	public ApplicationException(ErrorCode errorCode) {
		super(errorCode.getMessage());
		this.code = errorCode.getCode();
	}

	public String getCode() {
		return code;
	}
}
