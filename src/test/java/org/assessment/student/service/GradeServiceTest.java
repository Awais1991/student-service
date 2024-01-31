package org.assessment.student.service;

import org.assessment.student.dto.GradeDto;
import org.assessment.student.dto.SchoolDto;
import org.assessment.student.entity.Grade;
import org.assessment.student.entity.School;
import org.assessment.student.exception.ApplicationException;
import org.assessment.student.mapper.GradeMapper;
import org.assessment.student.repo.GradeRepository;
import org.assessment.student.validate.GradeRequestValidator;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class GradeServiceTest {

    @Mock
    private GradeRepository gradeRepository;

    @Mock
    private GradeMapper gradeMapper;

    @Mock
    private SchoolService schoolService;

    @InjectMocks
    private GradeService gradeService;

    @Mock
    private GradeRequestValidator gradeRequestValidator;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetGradeByUuidWithSchool() {
        // Arrange
        String uuid = "sampleUuid";
        Grade grade = new Grade();
        grade.setUuid(uuid);
        grade.setSchool(new School());

        GradeDto expectedDto = new GradeDto();
        expectedDto.setUuid(uuid);

        when(gradeRepository.findByUuid(uuid)).thenReturn(java.util.Optional.of(grade));
        when(gradeMapper.mapToDto(grade)).thenReturn(expectedDto);
        when(schoolService.getSchoolByUuid(grade.getSchool().getUuid())).thenReturn(new SchoolDto());

        // Act
        GradeDto result = gradeService.getGradeByUuid(uuid);

        // Assert
        assertNotNull(result);
        assertEquals(expectedDto, result);
        assertNotNull(result.getSchool());
    }

    @Test
    public void testGetGradeByUuidWithoutSchool() {
        // Arrange
        String uuid = "sampleUuid";
        Grade grade = new Grade();
        grade.setUuid(uuid);

        GradeDto expectedDto = new GradeDto();
        expectedDto.setUuid(uuid);

        when(gradeRepository.findByUuid(uuid)).thenReturn(java.util.Optional.of(grade));
        when(gradeMapper.mapToDto(grade)).thenReturn(expectedDto);

        // Act
        GradeDto result = gradeService.getGradeByUuid(uuid);

        // Assert
        assertNotNull(result);
        assertEquals(expectedDto, result);
        assertNull(result.getSchool());
    }

    @Test
    public void testCreateGradeSuccess() {
        // Arrange
        GradeDto gradeDto = new GradeDto();
        gradeDto.setUuid("grade-uuid");
        gradeDto.setGrade("123");
        SchoolDto schoolDto =new SchoolDto();
        schoolDto.setSchoolId("1234");
        gradeDto.setSchool(schoolDto);
        Grade gradeEntity = new Grade();
        gradeEntity.setUuid("grade-uuid");

        when(gradeRequestValidator.validateCreateGrade(gradeDto)).thenReturn(true);
        when(gradeRepository.existsByName(gradeDto.getGrade())).thenReturn(false);
        when(gradeMapper.mapToEntity(gradeDto)).thenReturn(gradeEntity);
        when(schoolService.getSchool(gradeDto.getSchool().getSchoolId())).thenReturn(new School());
        when(gradeRepository.save(gradeEntity)).thenReturn(gradeEntity);

        // Act
        String result = gradeService.createGrade(gradeDto);

        // Assert
        assertNotNull(result);
        assertEquals(gradeEntity.getUuid(), result);
    }

    @Test
    public void testCreateGradeValidationFailed() {
        // Arrange
        GradeDto gradeDto = new GradeDto();
        when(gradeRequestValidator.validateCreateGrade(gradeDto)).thenReturn(false);

        // Act and Assert
        assertThrows(ApplicationException.class, () -> gradeService.createGrade(gradeDto));
    }

    @Test
    public void testGetAllStudent() {
        // Arrange
        List<Grade> gradeEntities = new ArrayList<>();
        List<GradeDto> expectedGradeDtos = new ArrayList<>();
        SchoolDto schoolDto =new SchoolDto();
        schoolDto.setSchoolId("1223");

        when(gradeRepository.findAll()).thenReturn(gradeEntities);
        when(gradeMapper.mapToDto(any())).thenAnswer(invocation -> {
            Grade grade = invocation.getArgument(0);
            GradeDto gradeDto = new GradeDto();
            gradeDto.setSchool(schoolDto);
            return gradeDto;
        });

        // Act
        List<GradeDto> result = gradeService.getGrades();

        // Assert
        assertNotNull(result);
        assertEquals(expectedGradeDtos.size(), result.size());
        assertEquals(expectedGradeDtos, result);
    }

    @Test
    public void testGetAllStudentWhenNoData() {
        // Arrange
        when(gradeRepository.findAll()).thenReturn(Collections.emptyList());

        // Act
        List<GradeDto> result = gradeService.getGrades();

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testGetByUuidWhenGradeExists() {
        // Arrange
        String uuid = "some-uuid";
        Grade expectedGrade = new Grade();
        when(gradeRepository.findByUuid(uuid)).thenReturn(Optional.of(expectedGrade));

        // Act
        Grade result = gradeService.getByUuid(uuid);

        // Assert
        assertNotNull(result);
        assertSame(expectedGrade, result);
    }

    @Test
    public void testGetByUuidWhenGradeDoesNotExist() {
        // Arrange
        String uuid = "non-existent-uuid";
        when(gradeRepository.findByUuid(uuid)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(ApplicationException.class, () -> gradeService.getByUuid(uuid));
    }
}
