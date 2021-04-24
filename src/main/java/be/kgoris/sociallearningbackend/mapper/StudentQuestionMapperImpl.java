package be.kgoris.sociallearningbackend.mapper;

import be.kgoris.sociallearningbackend.dto.StudentQuestionDto;
import be.kgoris.sociallearningbackend.entities.StudentQuestion;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentQuestionMapperImpl implements StudentQuestionMapper {
    private final StudentMapper studentMapper;
    private final AnswerMapper answerMapper;
    private final QuestionMapper questionMapper;

    @Override
    public StudentQuestion fromDtoToModel(StudentQuestionDto studentQuestionDto) {
        return StudentQuestion.builder()
                .id(studentQuestionDto.getId())
                .student(studentQuestionDto.getStudent() != null
                        ? studentMapper.fromDtoToModel(studentQuestionDto.getStudent())
                        : null)
                .answer(studentQuestionDto.getAnswer() != null
                        ? answerMapper.fromDtoToModel(studentQuestionDto.getAnswer())
                        : null)
                .question(studentQuestionDto.getQuestion() != null
                        ? questionMapper.fromDtoToModel(studentQuestionDto.getQuestion())
                        : null)
                .locked(studentQuestionDto.isLocked())
                .build();
    }

    @Override
    public StudentQuestionDto fromModelToDto(StudentQuestion studentQuestion) {
        return StudentQuestionDto.builder()
                .id(studentQuestion.getId())
                .student(studentQuestion.getStudent() != null
                        ? studentMapper.fromModelToDto(studentQuestion.getStudent())
                        : null)
                .answer(studentQuestion.getAnswer() != null
                        ? answerMapper.fromModelToDto(studentQuestion.getAnswer())
                        : null)
                .question(studentQuestion.getQuestion() != null
                        ? questionMapper.fromModelToDto(studentQuestion.getQuestion())
                        : null)
                .locked(studentQuestion.isLocked())
                .build();
    }
}
