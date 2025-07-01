package org.agent.MedAgent.agent;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import reactor.core.publisher.Flux;
import dev.langchain4j.service.spring.AiService;

@AiService
public interface MedicalAgent {
    @SystemMessage(fromResource = "template.txt")
    Flux<String> chat(@MemoryId Long memoryId, @UserMessage String userMessage);
}
