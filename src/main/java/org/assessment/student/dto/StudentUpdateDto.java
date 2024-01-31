package org.assessment.student.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder(toBuilder = true)
public class StudentUpdateDto {

	@JsonProperty("gradeId")
	private String gradeUUID;
	@JsonProperty("mobileNumber")// Think
	private String mobileNumber;
	@JsonProperty("firstName")
	private String firstName;
	@JsonProperty("lastName")
	private String lastName;
	@JsonFormat(pattern = "yyyy-MM-dd")
	@JsonProperty("dateOfBirth")
	private LocalDate dateOfBirth;
}
