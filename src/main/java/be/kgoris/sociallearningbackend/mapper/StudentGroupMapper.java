package be.kgoris.sociallearningbackend.mapper;

import be.kgoris.sociallearningbackend.dto.StudentGroupDto;
import be.kgoris.sociallearningbackend.entities.StudentGroup;

public interface StudentGroupMapper {
    StudentGroup fromDtoToModel(StudentGroupDto studentGroupDto);
    StudentGroupDto fromModelToDto(StudentGroup studentGroup);
}
