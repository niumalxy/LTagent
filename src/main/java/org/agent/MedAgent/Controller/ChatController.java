package org.agent.MedAgent.Controller;

import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.ChatMemoryProvider;
import dev.langchain4j.service.spring.event.AiServiceRegisteredEvent;
import jakarta.annotation.Resource;
import jakarta.websocket.server.PathParam;
import org.agent.MedAgent.Mapper.ChatMapper;
import org.agent.MedAgent.Object.ChatItem;
import org.agent.MedAgent.Object.Response;
import org.agent.MedAgent.Object.Result;
import org.agent.MedAgent.Service.ChatSessionService;
import org.agent.MedAgent.agent.ChatMemorys;
import org.agent.MedAgent.agent.LocalChatHistory;
import org.agent.MedAgent.agent.MedicalAgent;
import org.agent.MedAgent.config.AiServiceRegisteredEventListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/chat")
public class ChatController {
    @Autowired
    private MedicalAgent medicalAgent;
    @Autowired
    private ChatMemoryProvider chatMemoryProvider;

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

    @GetMapping("/chat_history/{memoryId}")
    public void chat_init(@PathVariable Long memoryId) {
        System.out.println(LocalChatHistory.getMessage(memoryId));
    }
}
