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
public class SchoolUpdateDto {

	@JsonProperty("schoolId")
	private String schoolUniqueIdentifier;
	@JsonProperty("schoolName")
	private String schoolName;
	@JsonProperty("schoolLocation")
	private String schoolLocation;

}
