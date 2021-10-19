package be.kgoris.sociallearningbackend.dto;

import be.kgoris.sociallearningbackend.entities.Sentence;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LearningItemDto {
    private Integer id;
    private SentenceDto original;
    private SentenceDto translation;
    private Integer sequenceNumber;
}
