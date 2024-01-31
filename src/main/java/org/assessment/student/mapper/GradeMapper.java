package org.assessment.student.mapper;

import org.assessment.student.dto.GradeDto;
import org.assessment.student.dto.GradeUpdateDto;
import org.assessment.student.entity.Grade;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.UUID;

@Component
public class GradeMapper implements Mapper<GradeDto, Grade, GradeUpdateDto>{

	@Override
	public Grade mapToEntity(GradeDto gradeDto) {
		return Grade.builder()
				.uuid(UUID.randomUUID().toString())
				.name(gradeDto.getGrade())
				.build();
	}

	@Override
	public Grade updateEntity(GradeUpdateDto gradeUpdateDto, Grade grade) {
		if (StringUtils.hasLength(gradeUpdateDto.getGrade()))grade.setName(gradeUpdateDto.getGrade());
		return grade;
	}



	@Override
	public GradeDto mapToDto(Grade grade) {
		return GradeDto.builder()
				.uuid(grade.getUuid())
				.grade(grade.getName())
				.build();
	}
}
