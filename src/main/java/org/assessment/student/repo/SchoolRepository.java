package org.assessment.student.repo;

import org.assessment.student.entity.School;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SchoolRepository extends JpaRepository<School, Long> {
	Optional<School> findByUuid(String uuid);
	boolean existsByName(String name);
}
