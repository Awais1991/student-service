package org.assessment.student.service;

import org.assessment.student.dto.SchoolDto;
import org.assessment.student.entity.School;
import org.assessment.student.exception.ApplicationException;
import org.assessment.student.mapper.SchoolMapper;
import org.assessment.student.repo.SchoolRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Order;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class SchoolServiceTest {

    @Mock
    private SchoolRepository schoolRepository;

    @Mock
    private SchoolMapper schoolMapper;

    @InjectMocks
    private SchoolService schoolService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @Order(1)
    public void testGetSchoolByUuid_Positive() {
        // Create mock data
        String uuid = "12345";
        School school = new School();
        SchoolDto expectedSchoolDto = new SchoolDto();

        // Mock the repository method
        when(schoolRepository.findByUuid(uuid)).thenReturn(Optional.of(school));

        // Mock the mapper method
        when(schoolMapper.mapToDto(school)).thenReturn(expectedSchoolDto);

        // Call the method under test
        SchoolDto result = schoolService.getSchoolByUuid(uuid);

        // Assert the result
        assertEquals(expectedSchoolDto, result);
    }

    @Order(2)
    @Test(expected = ApplicationException.class)
    public void testGetSchoolByUuid_Failed() {
        // Create mock data
        String uuid = "12345";

        // Mock the repository method
        when(schoolRepository.findByUuid(uuid)).thenReturn(Optional.empty());

        // Call the method under test
        schoolService.getSchoolByUuid(uuid);
    }

    @Order(3)
    @Test
    public void testGetSchool_Positive() {
        String uuid = "12345";
        School school = new School();
        when(schoolRepository.findByUuid(uuid)).thenReturn(Optional.of(school));

        School result = schoolService.getSchool(uuid);

        assertNotNull(result);
        assertEquals(school, result);
        verify(schoolRepository, times(1)).findByUuid(uuid);
    }

    @Order(4)
    @Test
    public void testGetSchool_Negative() {
        String uuid = "12345";
        when(schoolRepository.findByUuid(uuid)).thenReturn(Optional.empty());

        assertThrows(ApplicationException.class, () -> schoolService.getSchool(uuid));

        verify(schoolRepository, times(1)).findByUuid(uuid);
    }

    @Order(5)
    @Test
    public void testCreateSchool_Positive() {
        // Arrange
        SchoolDto schoolDto = new SchoolDto();
        schoolDto.setSchoolId("123");
        schoolDto.setSchoolName("Test School");
        School school = new School();
        school.setUuid("1232");
        school.setName("Test School");


        when(schoolRepository.existsByName(schoolDto.getSchoolName())).thenReturn(false);
        when(schoolMapper.mapToEntity(schoolDto)).thenReturn(school);
        when(schoolRepository.save(any(School.class))).thenReturn(school);

        String result = schoolService.createSchool(schoolDto);

        // Assert
        assertNotNull(result);
        // Additional assertions can be added to validate the result
    }

    @Order(6)
    @Test
    public void testCreateSchool_Negative() {
        // Arrange
        SchoolDto schoolDto = new SchoolDto();
        schoolDto.setSchoolName("Test School");

        when(schoolRepository.existsByName(schoolDto.getSchoolName())).thenReturn(true);

        // Act and Assert
        assertThrows(ApplicationException.class, () -> schoolService.createSchool(schoolDto));
    }

    @Order(7)
    @Test
    public void getAllSchools_PositiveCase_ReturnsSchoolDtoList() {
        // Mock data
        School school1 = new School();
        school1.setUuid("1232");
        school1.setName("Test School");
        School school2 = new School();
        school2.setUuid("1232");
        school2.setName("Test School");
        List<School> schoolList = Arrays.asList(school1, school2);

        SchoolDto schoolDto1 = new SchoolDto().toBuilder().schoolId("1232").schoolName("Test School").build();
        SchoolDto schoolDto2 = new SchoolDto().toBuilder().schoolId("1232").schoolName("Test School").build();
        List<SchoolDto> expectedSchoolDtoList = Arrays.asList(schoolDto1, schoolDto2);

        // Mock the behavior of your dependencies
        when(schoolRepository.findAll()).thenReturn(schoolList);
        when(schoolMapper.mapToDto(school1)).thenReturn(schoolDto1);
        when(schoolMapper.mapToDto(school2)).thenReturn(schoolDto2);

        // Call the method
        List<SchoolDto> result = schoolService.getAllSchools();

        // Assertions
        assertNotNull(result);
        assertEquals(expectedSchoolDtoList.size(), result.size());
        // Add more assertions based on your specific requirements
    }

    @Order(8)
    @Test
    public void testGetAllSchoolsEmpty() {
        schoolRepository.deleteAll();
        List<SchoolDto> schools = schoolService.getAllSchools();
        assertNotNull(schools);
        assertEquals(0, schools.size()); // Negative test case
    }

}