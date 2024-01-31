package org.assessment.student.commen;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder(toBuilder = true)
public class ErrorInfo {

	private String code;
	private String message;


}
