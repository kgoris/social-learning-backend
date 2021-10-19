package be.kgoris.sociallearningbackend.mapper;

import be.kgoris.sociallearningbackend.dto.SentenceDto;
import be.kgoris.sociallearningbackend.entities.Sentence;

public interface SentenceMapper {
    SentenceDto fromModelToDto(Sentence sentence);
}
