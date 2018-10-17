package org.home;

import lombok.AllArgsConstructor;
import org.home.db.GlobalCache;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class MessageController {
    private final GlobalCache globalCache;

    @MessageMapping("/chat")
    @SendTo("/topic/message")
    public String sendMessage(MessageData data) {
        globalCache.update(data.getText() + "\n");
        return data.getText();
    }
}