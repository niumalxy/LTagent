package org.agent.MedAgent.agent;

import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import dev.langchain4j.memory.chat.ChatMemoryProvider;

import java.util.List;

@Configuration
public class ChatMemoryTool {

    //增加聊天记忆，并在AiService添加注解value
    @Bean
    public ChatMemory chatHistory() {
        return MessageWindowChatMemory.withMaxMessages(10);
    }
}
