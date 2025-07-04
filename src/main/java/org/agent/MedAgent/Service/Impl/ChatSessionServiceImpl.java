package org.agent.MedAgent.Service.Impl;

import dev.langchain4j.data.message.ChatMessage;
import org.agent.MedAgent.Service.ChatSessionService;
import org.agent.MedAgent.agent.LocalChatHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ChatSessionServiceImpl implements ChatSessionService {
    @Autowired
    ThreadPoolTaskExecutor executor;

    //TODO
    @Override
    public void Dump2Mongo(Long memoryId){
        List<ChatMessage> messages = LocalChatHistory.getMessage(memoryId);
    }
}
