package org.assessment.student.repo;

import org.assessment.student.entity.Grade;
import org.assessment.student.entity.School;
import org.assessment.student.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private GradeRepository gradeRepository;
    @Autowired
    private SchoolRepository schoolRepository;

    @Test
    public void testFindByUuid() {
        // Given
        Grade grade = new Grade();
        grade.setUuid("some-uuid");
        grade.setName("abc");
        grade.setId(1L);
        School school = new School();
        school.setUuid("some-uuid");
        school.setName("Some School");
        Student student = new Student();
        student.setUuid("some-student-uuid");
        student.setDateOfBirth(LocalDate.now());
        student.setFirstName("first");
        student.setLastName("last");
        student.setGuardianName("guardia");
        student.setGuardianRelation("father");
        student.setDateOfBirth(LocalDate.now());
        student.setJoiningDate(LocalDate.now());
        student.setGender("M");
        student.setMobileNumber("123456");
        student.setRollNo("123");
        grade.setSchool(school);
        schoolRepository.save(school);
        studentRepository.save(student);
        gradeRepository.save(grade);

        Optional<Student> foundStudent = studentRepository.findByUuid("some-student-uuid");
        assertTrue(foundStudent.isPresent());
        assertEquals("some-student-uuid", foundStudent.get().getUuid());
    }

    @Test
    public void testFindByRollNo() {
        // Given
        Grade grade = new Grade();
        grade.setUuid("some-uuid");
        grade.setName("abc");
        grade.setId(1L);
        School school = new School();
        school.setUuid("some-uuid");
        school.setName("Some School");
        Student student = new Student();
        student.setUuid("some-student-uuid");
        student.setDateOfBirth(LocalDate.now());
        student.setFirstName("first");
        student.setLastName("last");
        student.setGuardianName("guardia");
        student.setGuardianRelation("father");
        student.setDateOfBirth(LocalDate.now());
        student.setJoiningDate(LocalDate.now());
        student.setGender("M");
        student.setMobileNumber("123456");
        student.setRollNo("123");
        grade.setSchool(school);
        schoolRepository.save(school);
        studentRepository.save(student);
        gradeRepository.save(grade);

        Optional<Student> foundStudent = studentRepository.findByRollNo("12345");
        assertFalse(foundStudent.isPresent());
    }

    @Test
    public void testExistsByMobileNumber() {
        // Given
        Grade grade = new Grade();
        grade.setUuid("some-uuid");
        grade.setName("abc");
        grade.setId(1L);
        School school = new School();
        school.setUuid("some-uuid");
        school.setName("Some School");
        Student student = new Student();
        student.setUuid("some-student-uuid");
        student.setDateOfBirth(LocalDate.now());
        student.setFirstName("first");
        student.setLastName("last");
        student.setGuardianName("guardia");
        student.setGuardianRelation("father");
        student.setDateOfBirth(LocalDate.now());
        student.setJoiningDate(LocalDate.now());
        student.setGender("M");
        student.setMobileNumber("123456");
        student.setRollNo("123");
        grade.setSchool(school);
        schoolRepository.save(school);
        studentRepository.save(student);
        gradeRepository.save(grade);

        // When
        boolean exists = studentRepository.existsByMobileNumber("1234567890");
        assertFalse(exists);
    }

}

