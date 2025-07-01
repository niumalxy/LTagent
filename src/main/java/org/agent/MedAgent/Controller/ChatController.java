package org.agent.MedAgent.Controller;

import org.agent.MedAgent.Object.Response;
import org.agent.MedAgent.Object.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/medical")
public class ChatController {
    @GetMapping("/check")
    public Result check(){
        return Response.success();
    }

    @PostMapping("/chat_init")
    public String chat_init() {
        return "hello world";
    }
}
