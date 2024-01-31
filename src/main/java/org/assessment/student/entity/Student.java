package org.assessment.student.entity;

import jakarta.persistence.*;


import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder(toBuilder = true)
@Entity
@Table(name = "students")
public class Student {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "uuid", nullable = false, unique = true)
	private String uuid;
	@Column(name = "roll_no", nullable = false, unique = true)
	private String rollNo;
	@Column(name = "mobile_number", nullable = false, unique = true)
	private String mobileNumber;
	@Column(name = "first_name", nullable = false)
	private String firstName;
	@Column(name = "last_name", nullable = false)
	private String lastName;
	@Column(name = "guardian_name", nullable = false)
	private String guardianName;
	@Column(name = "guardian_relation", nullable = false)
	private String guardianRelation;
	@Column(name = "date_of_birth", nullable = false)
	private LocalDate dateOfBirth;
	@Column(name = "joining_date", nullable = false)
	private LocalDate joiningDate;
	@Column(name = "gender", nullable = false)
	private String gender;
	@ManyToOne
	@JoinColumn(name = "grade", referencedColumnName = "uuid")
	private Grade grade;


}
