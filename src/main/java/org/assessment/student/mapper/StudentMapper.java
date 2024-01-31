package org.assessment.student.mapper;

import org.assessment.student.dto.StudentDto;
import org.assessment.student.dto.StudentUpdateDto;
import org.assessment.student.entity.Student;
import org.assessment.student.util.RollNumberGenerator;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.UUID;

@Component
public class StudentMapper implements Mapper<StudentDto, Student, StudentUpdateDto> {

	@Override
	public Student mapToEntity(StudentDto studentDto) {
		return Student.builder()
				.uuid(UUID.randomUUID().toString())
				.mobileNumber(studentDto.getMobileNumber())
				.guardianName(studentDto.getGuardianName())
				.guardianRelation(studentDto.getGuardianRelation())
				.firstName(studentDto.getFirstName())
				.lastName(studentDto.getLastName())
				.dateOfBirth(studentDto.getDateOfBirth())
				.gender(studentDto.getGender())
				.rollNo(RollNumberGenerator.generateRollNo(10))
				.joiningDate(LocalDate.now())
				.build();
	}

	@Override
	public Student updateEntity(StudentUpdateDto studentDto, Student student) {
	    if (StringUtils.hasLength(studentDto.getMobileNumber())){student.setMobileNumber(studentDto.getMobileNumber());}
		if (StringUtils.hasLength(studentDto.getFirstName())){student.setFirstName(studentDto.getFirstName());}
		if (StringUtils.hasLength(studentDto.getLastName())){student.setLastName(studentDto.getLastName());}
		if (null!= studentDto.getDateOfBirth()){student.setDateOfBirth(studentDto.getDateOfBirth());}
	    return student;


	}


	@Override
	public StudentDto mapToDto(Student student) {
		return StudentDto.builder()
				.uuid(student.getUuid())
				.mobileNumber(student.getMobileNumber())
				.firstName(student.getFirstName())
				.lastName(student.getLastName())
				.dateOfBirth(student.getDateOfBirth())
				.gender(student.getGender())
				.rollNo(student.getRollNo())
				.guardianName(student.getGuardianName())
				.guardianRelation(student.getGuardianRelation())
				.joiningDate(student.getJoiningDate())
				.build();
	}
}
