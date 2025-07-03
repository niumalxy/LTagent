package org.agent.MedAgent.agent;

import dev.langchain4j.memory.chat.ChatMemoryProvider;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChatMemorys {
    //为不同用户设置不同的chatMemory
    @Bean
    public ChatMemoryProvider chatManager(){
        return memoryId -> MessageWindowChatMemory.builder().id(memoryId).maxMessages(10).build();
    }
}
