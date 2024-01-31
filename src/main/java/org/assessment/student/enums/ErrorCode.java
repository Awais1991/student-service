package org.assessment.student.enums;

public enum ErrorCode {
	INVALID_VALUE("100101", "invalid value"),
	UNABLE_TO_FIND_STUDENT("100102", "Unable to find student."),
	UNABLE_TO_CREATE_STUDENT("100103", "Unable to create student."),
	UNABLE_TO_CREATE_GRADE("100104", "Unable to grade"),
	UNABLE_TO_FIND_GRADE("100105", "Unable to grade"),
	UNABLE_TO_CREATE_SCHOOL("100106", "Unable to create school"),
	UNABLE_TO_FIND_SCHOOL("100107", "Unable to find school"),
	VALUE_REQ("100108", "value required"),
	UPDATE_STUDENT_FAILED("100109", "unable to update student"),
	;
	private String code;
	private String message;

	ErrorCode(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
}
