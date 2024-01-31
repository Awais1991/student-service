package org.assessment.student.controller;

import org.assessment.student.commen.ApiResponse;
import org.assessment.student.dto.StudentDto;
import org.assessment.student.dto.StudentUpdateDto;
import org.assessment.student.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/students")
public class StudentController {


	private final StudentService studentService;

	public StudentController(StudentService studentService) {
		this.studentService = studentService;
	}

	@GetMapping(path = "/studentId/{uuid}")
	public ResponseEntity<ApiResponse<StudentDto>> getStudentByUuid(@PathVariable String uuid){
		return ResponseEntity.ok(new ApiResponse<>(studentService.getStudentById(uuid, true), null));
	}

	@GetMapping(path = "/rollNo/{rollNo}")
	public ResponseEntity<ApiResponse<StudentDto>> getStudentByRollNo(@PathVariable String rollNo){
		return ResponseEntity.ok(new ApiResponse<>(studentService.getStudentById(rollNo, false), null));
	}

	@PostMapping(path = "/enroll")
	public ResponseEntity<ApiResponse<String>> getStudentByRollNo(@RequestBody StudentDto studentDto){
		return ResponseEntity.ok(new ApiResponse<>(studentService.enrollStudent(studentDto), null));
	}

	@PutMapping(path = "/rollNo/{rollNo}")
	public ResponseEntity<ApiResponse<StudentDto>> getStudentByRollNo(@PathVariable String rollNo, @RequestBody StudentUpdateDto studentDto){
		return ResponseEntity.ok(new ApiResponse<>(studentService.updateStudent(rollNo, studentDto), null));
	}

	@GetMapping
	public ResponseEntity<ApiResponse<List<StudentDto>>> getAllStudents(){
		return ResponseEntity.ok(new ApiResponse<>(studentService.getAllStudent(), null));
	}

	@GetMapping(path = "/{gradeId}")
	public ResponseEntity<ApiResponse<List<StudentDto>>> getAllStudentsByGrade(@PathVariable String gradeId){
		return ResponseEntity.ok(new ApiResponse<>(studentService.getAllStudentsOfGrade(gradeId), null));
	}

}
