package be.kgoris.sociallearningbackend.websocket;

import be.kgoris.sociallearningbackend.dto.ActivityDto;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {
    @MessageMapping("/activity")
    @SendTo("/topic/activity")
    public ActivityDto logActivity(ActivityDto activityDto) throws Exception {
        return activityDto;
    }
}
