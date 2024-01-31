package org.assessment.student.exception;

import org.assessment.student.commen.ApiResponse;
import org.assessment.student.commen.ErrorInfo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class CentralizedExceptionHandler {

	@ExceptionHandler(ApplicationException.class)
	public ResponseEntity<ApiResponse<Void>> handleApplicationException(ApplicationException ex){
		ApiResponse<Void> voidApiResponse = new ApiResponse<>(null, new ErrorInfo(ex.getCode(), ex.getMessage()));
		return ResponseEntity.badRequest().body(voidApiResponse);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiResponse<Void>> handleApplicationException(Exception ex){
		ApiResponse<Void> voidApiResponse = new ApiResponse<>(null, new ErrorInfo("100999", ex.getMessage()));
		return ResponseEntity.badRequest().body(voidApiResponse);
	}
}
