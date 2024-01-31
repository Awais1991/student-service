package org.assessment.student.service;

import org.assessment.student.dto.GradeDto;
import org.assessment.student.dto.StudentDto;
import org.assessment.student.dto.StudentUpdateDto;
import org.assessment.student.entity.Grade;
import org.assessment.student.entity.Student;
import org.assessment.student.exception.ApplicationException;
import org.assessment.student.mapper.GradeMapper;
import org.assessment.student.mapper.StudentMapper;
import org.assessment.student.repo.StudentRepository;
import org.assessment.student.validate.StudentRequestValidator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private GradeService gradeService;

    @Mock
    private StudentMapper studentMapper;

    @InjectMocks
    private StudentService studentService;

    @Mock
    private StudentRequestValidator studentRequestValidator;

    @Mock
    private GradeMapper gradeMapper;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @Order(1)
    @DisplayName("Test get student by id with valid uuid")
    public void testGetStudentByIdWithValidUuid() {
        String uuid = "valid-uuid";
        Student student = new Student();
        student.setUuid(uuid);
        GradeDto grade = new GradeDto();
        grade.setUuid("grade-uuid");
        StudentDto expectedStudentDto = new StudentDto();
        expectedStudentDto.setGrade(grade);
        Grade gradeEntity = new Grade();
        grade.setUuid("grade-uuid");
        student.setGrade(gradeEntity);

        when(studentRepository.findByUuid(uuid)).thenReturn(Optional.of(student));
        Grade studentGrade = student.getGrade();
        when(gradeService.getGradeByUuid(studentGrade.getUuid())).thenReturn(grade);
        when(studentMapper.mapToDto(student)).thenReturn(expectedStudentDto);

        StudentDto result = studentService.getStudentById(uuid, true);

        assertNotNull(result);
        assertEquals(expectedStudentDto, result);
    }

    @Test
    @Order(2)
    @DisplayName("Test get student by id with valid roll no")
    public void testGetStudentByIdWithValidRollNo() {
        String rollNo = "valid-roll-no";
        Student student = new Student();
        student.setRollNo(rollNo);
        GradeDto grade = new GradeDto();
        grade.setUuid("grade-uuid");
        StudentDto expectedStudentDto = new StudentDto();
        expectedStudentDto.setGrade(grade);
        Grade gradeEntity = new Grade();
        grade.setUuid("grade-uuid");
        student.setGrade(gradeEntity);

        when(studentRepository.findByRollNo(rollNo)).thenReturn(Optional.of(student));
        when(gradeService.getGradeByUuid(student.getGrade().getUuid())).thenReturn(grade);
        when(studentMapper.mapToDto(student)).thenReturn(expectedStudentDto);

        StudentDto result = studentService.getStudentById(rollNo, false);

        assertNotNull(result);
        assertEquals(expectedStudentDto, result);
    }

    @Test
    @Order(3)
    @DisplayName("Test get student by id with invalid id")
    public void testGetStudentByIdWithInvalidId() {
        String invalidId = "invalid-id";

        when(studentRepository.findByUuid(invalidId)).thenReturn(Optional.empty());
        when(studentRepository.findByRollNo(invalidId)).thenReturn(Optional.empty());

        assertThrows(ApplicationException.class, () -> studentService.getStudentById(invalidId, true));
    }

    @Test(expected = ApplicationException.class)
    @Order(4)
    @DisplayName("Test enroll student with existing mobile number")
    public void testEnrollStudent_ExistingMobileNumber() {
        // Create a valid student
        StudentDto studentDto = new StudentDto();
        studentDto.setMobileNumber("1234567890");
        Student student = new Student();
        student.setMobileNumber("1234567890");
        GradeDto gradeDto = new GradeDto();
        gradeDto.setUuid("grade_uuid");
        Grade grade = new Grade();
        grade.setUuid("grade_uuid");
        student.setGrade(grade);
        studentDto.setGrade(gradeDto);

        // Mock the dependencies
        when(studentRequestValidator.validateCreateStudent(studentDto)).thenReturn(true);
        when(studentRepository.existsByMobileNumber(student.getMobileNumber())).thenReturn(true);

        studentService.enrollStudent(studentDto);
    }

    @Test(expected = ApplicationException.class)
    @Order(5)
    @DisplayName("Test enroll student for invalid Student")
    public void testEnrollStudent_InvalidStudent() {
        // Create an invalid student
        StudentDto studentDto = new StudentDto();
        studentDto.setMobileNumber("1234567890");
        GradeDto gradeDto = new GradeDto();
        gradeDto.setUuid("grade_uuid");
        Grade grade = new Grade();
        grade.setUuid("grade_uuid");
        studentDto.setGrade(gradeDto);

        // Mock the dependencies
        when(studentRequestValidator.validateCreateStudent(studentDto)).thenReturn(false);

        // Call the method under test
        studentService.enrollStudent(studentDto);
    }

    @Test
    @Order(6)
    @DisplayName("Test update student with valid conditions")
    public void testUpdateStudent_Positive() {
        // Create mock data
        String rollNo = "12345";
        StudentUpdateDto studentUpdateDto = new StudentUpdateDto();
        studentUpdateDto.setGradeUUID("grade-uuid");
        Student student = new Student();
        Grade grade = new Grade();
        grade.setUuid("grade-uuid1");
        GradeDto gradeDto = new GradeDto();
        gradeDto.setUuid("grade-uuid1");
        student.setGrade(grade);
        StudentDto expectedStudentDto = new StudentDto();

        // Mock the repository method
        when(studentRepository.findByRollNo(rollNo)).thenReturn(Optional.of(student));

        // Mock the mapper method
        when(studentMapper.updateEntity(studentUpdateDto, student)).thenReturn(student);
        when(gradeService.getByUuid(studentUpdateDto.getGradeUUID())).thenReturn(grade);
        when(studentMapper.mapToDto(student)).thenReturn(expectedStudentDto);
        when(gradeService.getGradeByUuid(student.getGrade().getUuid())).thenReturn(gradeDto);

        // Call the method under test
        StudentDto result = studentService.updateStudent(rollNo, studentUpdateDto);
        assertEquals(expectedStudentDto, result);
    }


    @Test
    @Order(7)
    @DisplayName("Test update student non existing student throws Exception")
    public void updateStudent_NonExistingStudent_ThrowsException() {
        // Mock data
        String rollNo = "nonExistingRollNo";
        StudentUpdateDto studentUpdateDto = new StudentUpdateDto(/* your update data */);

        when(studentRepository.findByRollNo(rollNo)).thenReturn(java.util.Optional.empty());
        assertThrows(ApplicationException.class, () -> {
            studentService.updateStudent(rollNo, studentUpdateDto);
        });

        // Optionally, you can verify that certain methods were not called
        verify(studentMapper, never()).updateEntity(any(), any());
        verify(gradeService, never()).getByUuid(any());
        verify(studentRepository, never()).save(any());
        verify(studentMapper, never()).mapToDto(any());
        verify(gradeMapper, never()).mapToDto(any());
    }

    @Test
    @Order(8)
    @DisplayName("Test get all students")
    public void testGetAllStudentPositive() {
        // Prepare mock data
        List<Student> students = new ArrayList<>();
        Student student =new Student();
        Grade grade = new Grade();
        grade.setUuid("grade-uuid");
        student.setUuid("abc");
        student.setGrade(grade);
        students.add(student);
        student = new Student();
        grade.setUuid("grade-uuid-2");
        student.setGrade(grade);
        student.setUuid("abd");
        students.add(student);

        List<StudentDto> expectedStudentDtos = students.stream()
                .map(student1 -> {
                    StudentDto studentDto = new StudentDto();
                    studentDto.setGrade(gradeMapper.mapToDto(student1.getGrade()));
                    return studentDto;
                })
                .collect(Collectors.toList());

        when(studentRepository.findAll()).thenReturn(students);

        when(studentMapper.mapToDto(students.get(0))).thenReturn(expectedStudentDtos.get(0));
        when(studentMapper.mapToDto(students.get(1))).thenReturn(expectedStudentDtos.get(1));
        when(gradeMapper.mapToDto(students.get(0).getGrade())).thenReturn(expectedStudentDtos.get(0).getGrade());
        when(gradeMapper.mapToDto(students.get(1).getGrade())).thenReturn(expectedStudentDtos.get(1).getGrade());

        List<StudentDto> result = studentService.getAllStudent();

        // Assert the result
        assertEquals(expectedStudentDtos, result);
    }

    @Test
    @Order(9)
    @DisplayName("Test get all student empty")
    public void testGetAllStudentEmpty() {
        List<Student> students = new ArrayList<>();
        when(studentRepository.findAll()).thenReturn(students);
        List<StudentDto> result = studentService.getAllStudent();
        Assert.assertTrue(result.isEmpty());
    }

    @Test(expected = ApplicationException.class)
    @Order(10)
    public void testUpdateStudent_Failed() {
        // Create mock data
        String rollNo = "12345";
        StudentUpdateDto studentUpdateDto = new StudentUpdateDto();
        studentUpdateDto.setGradeUUID("grade-uuid");

        // Mock the repository method
        when(studentRepository.findByRollNo(rollNo)).thenReturn(Optional.empty());

        // Call the method under test
        studentService.updateStudent(rollNo, studentUpdateDto);
    }


}