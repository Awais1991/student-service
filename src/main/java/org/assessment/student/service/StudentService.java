package org.assessment.student.service;

import org.assessment.student.dto.StudentDto;
import org.assessment.student.dto.StudentUpdateDto;
import org.assessment.student.entity.Student;
import org.assessment.student.enums.ErrorCode;
import org.assessment.student.exception.ApplicationException;
import org.assessment.student.mapper.GradeMapper;
import org.assessment.student.mapper.StudentMapper;
import org.assessment.student.repo.StudentRepository;
import org.assessment.student.validate.StudentRequestValidator;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
	private final StudentRepository studentRepository;
	private final StudentMapper studentMapper;
	private final StudentRequestValidator studentRequestVaidator;
	private final GradeService gradeService;
	private final GradeMapper gradeMapper;

	public StudentService(StudentRepository studentRepository, StudentMapper studentMapper,
						  StudentRequestValidator studentRequestVaidator, GradeService gradeService, GradeMapper gradeMapper) {
		this.studentRepository = studentRepository;
		this.studentMapper = studentMapper;
		this.studentRequestVaidator = studentRequestVaidator;
		this.gradeService = gradeService;
		this.gradeMapper = gradeMapper;
	}

	public StudentDto getStudentById(String id, boolean isUuid) {
		return (isUuid ? studentRepository.findByUuid(id) : studentRepository.findByRollNo(id))
				.map(student -> {
					StudentDto studentDto = studentMapper.mapToDto(student);
					studentDto.setGrade(gradeService.getGradeByUuid(student.getGrade().getUuid()));
					return studentDto;
				})
				.orElseThrow(() -> new ApplicationException(ErrorCode.UNABLE_TO_FIND_STUDENT));
	}


	public String enrollStudent(StudentDto student) {
		return Optional.ofNullable(student).filter(studentRequestVaidator::validateCreateStudent)
			.filter(student1 -> !studentRepository.existsByMobileNumber(student1.getMobileNumber()))
			.map(studentMapper::mapToEntity)
				.map(student1 -> {
					student1.setGrade(gradeService.getByUuid(student.getGrade().getUuid()));
					return student1;})
			.map(studentRepository::save)
			.map(Student::getRollNo)
			.orElseThrow(() -> new ApplicationException(ErrorCode.UNABLE_TO_CREATE_STUDENT));
	}

	public StudentDto updateStudent(String rollNo, StudentUpdateDto studentUpdateDto) {
		return studentRepository.findByRollNo(rollNo).map(student -> {
			student = studentMapper.updateEntity(studentUpdateDto, student);
			if (StringUtils.hasLength(studentUpdateDto.getGradeUUID())){
				student.setGrade(gradeService.getByUuid(studentUpdateDto.getGradeUUID()));
			}
			return student;
		}).map(student -> {
			StudentDto studentDto = studentMapper.mapToDto(student);
			studentDto.setGrade(gradeService.getGradeByUuid(student.getGrade().getUuid()));
			return studentDto;
		}).orElseThrow(() -> new ApplicationException(ErrorCode.UPDATE_STUDENT_FAILED));





	}

	public List<StudentDto> getAllStudent() {
		return studentRepository.findAll().stream().map(student -> {
			var s = studentMapper.mapToDto(student);
			s.setGrade(gradeMapper.mapToDto(student.getGrade()));
			return s;
		}).toList();
	}
	public List<StudentDto> getAllStudentsOfGrade(String gradeId){

		return studentRepository.findAllByGrade_Uuid(gradeId).stream()
				.map(student -> {
					var s = studentMapper.mapToDto(student);
					s.setGrade(gradeMapper.mapToDto(student.getGrade()));
					return s;
				}).toList();
	}

}
