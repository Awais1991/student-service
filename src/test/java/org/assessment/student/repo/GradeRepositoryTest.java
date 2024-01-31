
package org.assessment.student.repo;

import org.assessment.student.entity.Grade;
import org.assessment.student.entity.School;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class GradeRepositoryTest {

    @Autowired
    private GradeRepository gradeRepository;
    @Autowired
    private SchoolRepository schoolRepository;

    @Test
    public void testFindByUuid() {
        // Given
        School school = new School();
        school.setUuid("some-uuid");
        school.setName("Some School");
        Grade grade = new Grade();
        grade.setUuid("some-uuid");
        grade.setName("abc");
        grade.setId(1L);
        grade.setSchool(school);
        schoolRepository.save(school);
        gradeRepository.save(grade);

        // When
        Optional<Grade> foundGrade = gradeRepository.findByUuid("some-uuid");

        // Then
        assertTrue(foundGrade.isPresent());
        assertEquals("some-uuid", foundGrade.get().getUuid());
        assertEquals("Some School", foundGrade.get().getSchool().getName());
    }

    @Test
    public void testExistsByName() {
        // Given
        School school = new School();
        school.setUuid("some-uuid");
        school.setName("Some School");
        Grade grade = new Grade();
        grade.setUuid("some-uuid");
        grade.setName("abc");
        grade.setId(1L);
        grade.setSchool(school);
        schoolRepository.save(school);
        gradeRepository.save(grade);

        // When
        boolean exists = gradeRepository.existsByName("abc");

        // Then
        assertTrue(exists);
    }

}
