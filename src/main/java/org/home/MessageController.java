package org.home;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class MessageController {

    @MessageMapping("/chat")
    @SendTo("/topic/message")
    public String sendMessage(MessageData data) {
        return HtmlUtils.htmlEscape(data.getText());
    }
}