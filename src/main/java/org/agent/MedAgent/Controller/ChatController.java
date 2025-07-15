package org.agent.MedAgent.Controller;

import dev.langchain4j.memory.chat.ChatMemoryProvider;
import org.agent.MedAgent.Object.ChatItem;
import org.agent.MedAgent.Object.Response;
import org.agent.MedAgent.Object.Result;
import org.agent.MedAgent.agent.LocalChatHistory;
import org.agent.MedAgent.agent.MedicalAgent;
import org.agent.MedAgent.utils.GlobalTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
        return medicalAgent.chat(GlobalTool.MemoryIdGenerater(chatItem.getMemoryId()), chatItem.getMessage(), currentDate);
    }


}
