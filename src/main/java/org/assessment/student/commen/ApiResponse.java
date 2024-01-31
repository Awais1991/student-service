package org.assessment.student.commen;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder(toBuilder = true)
public class ApiResponse<T> {

	private T data;
	private ErrorInfo error;
}
