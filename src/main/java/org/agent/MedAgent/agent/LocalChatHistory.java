package org.agent.MedAgent.agent;

import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.memory.ChatMemory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class LocalChatHistory {
    public static ConcurrentHashMap<Object, ChatMemory> chat_history;
    //用于存储不同memoryId的历史记录
    @Autowired
    public LocalChatHistory(){
        LocalChatHistory.chat_history = new ConcurrentHashMap<Object, ChatMemory>();
    }

    public static List<ChatMessage> getMessage(Long memoryId){
        return LocalChatHistory.chat_history.get(memoryId).messages();
    }
}
