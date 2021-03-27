package be.kgoris.sociallearningbackend.mapper;

import be.kgoris.sociallearningbackend.dto.StudentQuestionDto;
import be.kgoris.sociallearningbackend.entities.StudentQuestion;
import org.springframework.stereotype.Service;

@Service
public class StudentQuestionMapperImpl implements StudentQuestionMapper {
    @Override
    public StudentQuestion fromDtoToModel(StudentQuestionDto studentQuestionDto) {
        return null;
    }

    @Override
    public StudentQuestionDto fromModelToDto(StudentQuestion studentQuestion) {
        return null;
    }
}
