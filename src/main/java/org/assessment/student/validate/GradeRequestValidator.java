package org.assessment.student.validate;

import org.assessment.student.dto.GradeDto;
import org.springframework.stereotype.Component;

@Component
public class GradeRequestValidator implements RequestValidator {

    public boolean validateCreateGrade(GradeDto gradeDto){
        validateNotNull(gradeDto, "Body is empty");
        validateNotBlank(gradeDto.getGrade(), "grade name is empty");
        validateNotNull(gradeDto.getSchool(), "schoolId is required");
        validateNotBlank(gradeDto.getSchool().getSchoolId(), "schoolId is required");
        return true;
    }

}
