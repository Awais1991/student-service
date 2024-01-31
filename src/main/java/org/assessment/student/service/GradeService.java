package org.assessment.student.service;

import org.assessment.student.dto.GradeDto;
import org.assessment.student.entity.Grade;
import org.assessment.student.enums.ErrorCode;
import org.assessment.student.exception.ApplicationException;
import org.assessment.student.mapper.GradeMapper;
import org.assessment.student.repo.GradeRepository;
import org.assessment.student.validate.GradeRequestValidator;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GradeService {
	private final GradeRepository gradeRepository;
	private final GradeMapper gradeMapper;
	private final SchoolService schoolService;
	private final GradeRequestValidator gradeRequestValidator;

	public GradeService(GradeRepository gradeRepository, GradeMapper gradeMapper, SchoolService schoolService,
						GradeRequestValidator gradeRequestValidator) {
		this.gradeRepository = gradeRepository;
		this.gradeMapper = gradeMapper;
		this.schoolService = schoolService;
		this.gradeRequestValidator = gradeRequestValidator;
	}

	public GradeDto getGradeByUuid(String uuid) {
		return gradeRepository.findByUuid(uuid)
			.map(grade -> {
				GradeDto gradeDto = gradeMapper.mapToDto(grade);
				if (null!= grade.getSchool()){
					gradeDto.setSchool(schoolService.getSchoolByUuid(grade.getSchool().getUuid()));
				}
				return gradeDto;
			})
			.orElseThrow(() -> new ApplicationException(ErrorCode.UNABLE_TO_FIND_GRADE));
	}

	public String createGrade(GradeDto grade) {
		return Optional.ofNullable(grade).filter(gradeRequestValidator::validateCreateGrade)
				.filter(gradeDto -> !gradeRepository.existsByName(grade.getGrade()))
			.map(gradeDto -> {
				Grade grade1 = gradeMapper.mapToEntity(gradeDto);
				grade1.setSchool(schoolService.getSchool(grade.getSchool().getSchoolId()));
				return grade1;
			})
			.map(gradeRepository::save).map(Grade::getUuid)
			.orElseThrow(() -> new ApplicationException(ErrorCode.UNABLE_TO_CREATE_GRADE));
	}

	public List<GradeDto> getGrades() {
		return gradeRepository.findAll().stream().map(grade -> {
			GradeDto gradeDto = gradeMapper.mapToDto(grade);
			if (null!= grade.getSchool()){
				gradeDto.setSchool(schoolService.getSchoolByUuid(grade.getSchool().getUuid()));
			}
			return gradeDto;
		}).collect(Collectors.toList());
	}

	public Grade getByUuid(String uuid){
		return gradeRepository.findByUuid(uuid)
				.orElseThrow(() -> new ApplicationException(ErrorCode.UNABLE_TO_FIND_GRADE));
	}

}
