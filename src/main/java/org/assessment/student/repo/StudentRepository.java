package org.assessment.student.repo;

import org.assessment.student.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
	Optional<Student> findByUuid(String uuid);
	Optional<Student> findByRollNo(String rollNo);
	boolean existsByMobileNumber(String mobileNo);
	List<Student>  findAllByGrade_Uuid(String gradeId);
}
