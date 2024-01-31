package org.assessment.student.mapper;

import org.assessment.student.dto.SchoolDto;
import org.assessment.student.dto.SchoolUpdateDto;
import org.assessment.student.entity.School;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.UUID;

@Component
public class SchoolMapper implements Mapper<SchoolDto, School, SchoolUpdateDto> {

    @Override
    public School mapToEntity(SchoolDto schoolDto) {
        return School.builder()
                .uuid(UUID.randomUUID().toString())
                .name(schoolDto.getSchoolName())
                .location(schoolDto.getSchoolLocation())
                .build();
    }

    @Override
    public School updateEntity(SchoolUpdateDto schoolDto, School school) {
        if (StringUtils.hasLength(schoolDto.getSchoolName())) {
            school.setName(schoolDto.getSchoolName());
        }
        if (StringUtils.hasLength(schoolDto.getSchoolLocation())) {
            school.setLocation(schoolDto.getSchoolLocation());
        }
        return school;

    }

    @Override
    public SchoolDto mapToDto(School school) {
        return SchoolDto.builder()
                .schoolId(school.getUuid())
                .schoolName(school.getName())
                .schoolLocation(school.getLocation())
                .build();
    }
}
