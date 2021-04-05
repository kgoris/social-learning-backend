package be.kgoris.sociallearningbackend.mapper;

import be.kgoris.sociallearningbackend.dto.StudentGroupDto;
import be.kgoris.sociallearningbackend.entities.StudentGroup;
import org.springframework.stereotype.Service;

@Service
public class StudentGroupMapperImpl implements StudentGroupMapper {
    @Override
    public StudentGroup fromDtoToModel(StudentGroupDto studentGroupDto) {
        return null;
    }

    @Override
    public StudentGroupDto fromModelToDto(StudentGroup studentGroup) {
        return null;
    }
}
