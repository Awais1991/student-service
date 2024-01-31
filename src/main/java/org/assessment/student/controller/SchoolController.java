package org.assessment.student.controller;

import jakarta.validation.Valid;
import org.assessment.student.commen.ApiResponse;
import org.assessment.student.dto.SchoolDto;
import org.assessment.student.service.SchoolService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping(path = "/api/schools")
public class SchoolController {


	private final SchoolService schoolService;

	public SchoolController(SchoolService schoolService) {
		this.schoolService = schoolService;
	}

	@GetMapping(path = "/{uuid}")
	public ResponseEntity<ApiResponse<SchoolDto>> getSchoolByUuid(@PathVariable String uuid){
		return ResponseEntity.ok(new ApiResponse<>(schoolService.getSchoolByUuid(uuid), null));
	}

	@PostMapping
	public ResponseEntity<ApiResponse<String>> createSchool(@Valid @RequestBody SchoolDto school){
		return ResponseEntity.ok(new ApiResponse<>(schoolService.createSchool(school), null));
	}
	@GetMapping
	public ResponseEntity<ApiResponse<List<SchoolDto>>> getAllSchools(){
		return ResponseEntity.ok(new ApiResponse<>(schoolService.getAllSchools(), null));
	}



}
