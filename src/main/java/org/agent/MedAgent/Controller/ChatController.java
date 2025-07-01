package org.agent.MedAgent.Controller;

import org.agent.MedAgent.Object.ChatItem;
import org.agent.MedAgent.Object.Response;
import org.agent.MedAgent.Object.Result;
import org.agent.MedAgent.agent.MedicalAgent;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/medical")
public class ChatController {
    @GetMapping("/check")
    public Result<Void> check(){
        return Response.success();
    }
    @PostMapping(value = "/chat", produces = "text/stream;charset=utf-8")
    public Flux<String> chat(@RequestBody ChatItem chatItem)  {
        return MedicalAgent.chat(chatItem.getMemoryId(), chatItem.getMessage());
    }

    @PostMapping("/chat_init")
    public String chat_init() {
        return "hello world";
    }
}
