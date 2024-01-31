package org.assessment.student.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder(toBuilder = true)
public class GradeUpdateDto {
	@JsonProperty("gradeId")
	private String uuid;
	@JsonProperty("grade")
	private String grade;
	@JsonProperty("school")
	private SchoolDto school;
}
