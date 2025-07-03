package org.agent.MedAgent.agent;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;
import dev.langchain4j.service.spring.AiServiceWiringMode;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import dev.langchain4j.service.spring.AiService;
import org.agent.MedAgent.Prompt.MedicalPrompt;

@AiService(wiringMode = AiServiceWiringMode.EXPLICIT,
        //streamingChatModel = "openAiChatModel",
        chatModel = "openAiChatModel",
        chatMemoryProvider = "ChatManager"
)
public interface MedicalAgent {
    @SystemMessage(MedicalPrompt.start)
    String chat(@MemoryId Long memoryId, @UserMessage String userMessage, @V("current_date") String current_date);
//    @SystemMessage(MedicalPrompt.start)
//    Flux<String> stream_chat(@UserMessage String userMessage, @V("current_date") String current_date);
}
