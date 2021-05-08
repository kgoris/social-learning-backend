package be.kgoris.sociallearningbackend.websocket;

import be.kgoris.sociallearningbackend.dto.StudentDto;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {
    @MessageMapping("/hello")
    @SendTo("/topic/hi")
    public ActivityDto greeting(StudentDto studentDto) throws Exception {
        return ActivityDto.builder()
                .studentDto(studentDto)
                .buttonClicked("a button")
                .build();
    }
}
