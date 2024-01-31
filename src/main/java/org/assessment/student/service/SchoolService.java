package org.assessment.student.service;

import org.assessment.student.dto.SchoolDto;
import org.assessment.student.entity.School;
import org.assessment.student.enums.ErrorCode;
import org.assessment.student.exception.ApplicationException;
import org.assessment.student.mapper.SchoolMapper;
import org.assessment.student.repo.SchoolRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SchoolService {
	private final SchoolRepository schoolRepository;
	private final SchoolMapper schoolMapper;

	public SchoolService(SchoolRepository schoolRepository, SchoolMapper schoolMapper) {
		this.schoolRepository = schoolRepository;
		this.schoolMapper = schoolMapper;
	}

	public SchoolDto getSchoolByUuid(String uuid) {
		return schoolRepository.findByUuid(uuid)
			.map(schoolMapper::mapToDto)
			.orElseThrow(() -> new ApplicationException(ErrorCode.UNABLE_TO_FIND_SCHOOL));
	}

	public School getSchool(String uuid) {
		return schoolRepository.findByUuid(uuid)
				.orElseThrow(() -> new ApplicationException(ErrorCode.UNABLE_TO_FIND_SCHOOL));
	}

	public String createSchool(SchoolDto schoolDto) {
		return Optional.ofNullable(schoolDto)
			.filter(school1 -> !schoolRepository.existsByName(school1.getSchoolName()))
			.map(schoolMapper::mapToEntity)
			.map(schoolRepository::save)
			.map(School::getUuid)
			.orElseThrow(() -> new ApplicationException(ErrorCode.UNABLE_TO_CREATE_SCHOOL));
	}

	public List<SchoolDto> getAllSchools() {
		return schoolRepository.findAll().stream().map(schoolMapper::mapToDto).collect(Collectors.toList());
	}

}
