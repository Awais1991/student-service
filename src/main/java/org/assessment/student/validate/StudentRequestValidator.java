package org.assessment.student.validate;

import org.assessment.student.dto.StudentDto;
import org.springframework.stereotype.Component;

@Component
public class StudentRequestValidator implements RequestValidator{

    public boolean validateCreateStudent(StudentDto studentDto){
        validateNotNull(studentDto, "Body is empty");
        validateNotBlank(studentDto.getGuardianName(), "Guardian Name cannot be blank");
        validateNotBlank(studentDto.getGuardianRelation(), "Guardian Relation cannot be blank");
        validateNotBlank(studentDto.getMobileNumber(), "mobileNumber cannot be blank");
        validateNotNull(studentDto.getDateOfBirth(), "mobileNumber cannot be blank");
        validateNotBlank(studentDto.getFirstName(), "firstName cannot be blank");
        validateNotBlank(studentDto.getLastName(), "lastName cannot be blank");
        validateNotBlank(studentDto.getGender(), "Gender cannot be blank");
        validateNotNull(studentDto.getGrade(), "grade is empty");
        validateNotBlank(studentDto.getGrade().getUuid(), "cannot be blank");
        return true;
    }

}
