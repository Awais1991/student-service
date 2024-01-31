package org.assessment.student.repo;

import org.assessment.student.entity.School;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class SchoolRepositoryTest {

    @Autowired
    private SchoolRepository schoolRepository;


    @Test
    public void testFindByUuid() {
        // Given
        School school = new School();
        school.setUuid("some-school-uuid");
        school.setName("test");
        schoolRepository.save(school);

        // When
        Optional<School> foundSchool = schoolRepository.findByUuid("some-school-uuid");

        // Then
        assertTrue(foundSchool.isPresent());
        assertEquals("some-school-uuid", foundSchool.get().getUuid());
    }

    @Test
    public void testExistsByName() {
        // Given
        School school = new School();
        school.setName("SchoolName");
        school.setUuid("test");
        schoolRepository.save(school);

        // When
        boolean exists = schoolRepository.existsByName("SchoolName");

        // Then
        assertTrue(exists);
    }

    @Test
    public void testExistsByName_NotExist() {
        // Given
        School school = new School();
        school.setName("SchoolName");
        school.setUuid("test");
        schoolRepository.save(school);

        // When
        boolean exists = schoolRepository.existsByName("wrongName");
        assertFalse(exists);
    }

    // Add more tests as needed

}

