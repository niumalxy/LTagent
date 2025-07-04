package org.agent.MedAgent.agent;

import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.ChatMemoryProvider;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.openai.internal.chat.Message;
import org.agent.MedAgent.store.RedisChatMemoryStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class ChatMemorys {
    @Autowired
    private RedisChatMemoryStore redisChatMemoryStore;
    //为不同用户设置不同的chatMemory
    @Bean
    public ChatMemoryProvider ChatManager(){
        return memoryId -> {
            ChatMemory manager = MessageWindowChatMemory.builder().id(memoryId).maxMessages(10).chatMemoryStore(redisChatMemoryStore).build();
            LocalChatHistory.chat_history.put(memoryId, manager);
            return manager;
        };
    }
}
