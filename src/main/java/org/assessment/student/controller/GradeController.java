package org.assessment.student.controller;

import jakarta.validation.Valid;
import org.assessment.student.commen.ApiResponse;
import org.assessment.student.dto.GradeDto;
import org.assessment.student.service.GradeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping(path = "/api/grades")
public class GradeController {


	private final GradeService gradeService;

	public GradeController(GradeService gradeService) {
		this.gradeService=gradeService;
	}

	@GetMapping(path = "/{uuid}")
	public ResponseEntity<ApiResponse<GradeDto>> getGradeByUuid(@PathVariable String uuid){
		return ResponseEntity.ok(new ApiResponse<>(gradeService.getGradeByUuid(uuid), null));
	}

	@PostMapping
	public ResponseEntity<ApiResponse<String>> createGrade(@Valid @RequestBody GradeDto grade){
		return ResponseEntity.ok(new ApiResponse<>(gradeService.createGrade(grade), null));
	}
	@GetMapping
	public ResponseEntity<ApiResponse<List<GradeDto>>> getAllGrades(){
		return ResponseEntity.ok(new ApiResponse<>(gradeService.getGrades(), null));
	}



}
