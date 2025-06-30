package org.agent.MedAgent.Controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/medical")
public class ChatController {

    @PostMapping("/chat_init")
    public String chat_init() {
        return "hello world";
    }
}
