package org.agent.MedAgent.Controller;

import jakarta.annotation.Resource;
import org.agent.MedAgent.Mapper.ChatMapper;
import org.agent.MedAgent.Object.ChatItem;
import org.agent.MedAgent.Object.Response;
import org.agent.MedAgent.Object.Result;
import org.agent.MedAgent.Service.ChatSessionService;
import org.agent.MedAgent.agent.MedicalAgent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/chat")
public class ChatController {
    @Autowired
    private MedicalAgent medicalAgent;

    @GetMapping("/check")
    public Result<Void> check(){
        return Response.success();
    }

    @PostMapping("/chat")
    public String chat(@RequestBody ChatItem chatItem)  {
        LocalDate now = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String currentDate = now.format(formatter);
        return medicalAgent.chat(chatItem.getMemoryId(), chatItem.getMessage(), currentDate);
    }

    @PostMapping("/chat_init")
    public String chat_init() {
        return "hello world";
    }
}
