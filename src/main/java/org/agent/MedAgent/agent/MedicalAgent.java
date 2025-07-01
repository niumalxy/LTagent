package org.agent.MedAgent.agent;

import reactor.core.CoreSubscriber;
import reactor.core.publisher.Flux;
import dev.langchain4j.service.spring.AiService;

@AiService
public class MedicalAgent {
    //TODO
    public Flux<String> chat(Long memoryId, String message){

    }
}
