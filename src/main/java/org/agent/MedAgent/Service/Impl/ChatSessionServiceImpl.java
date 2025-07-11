package org.agent.MedAgent.Service.Impl;

import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.data.message.ChatMessageSerializer;
import org.agent.MedAgent.Object.ChatItem;
import org.agent.MedAgent.Object.RedisChatHistory;
import org.agent.MedAgent.Service.ChatSessionService;
import org.agent.MedAgent.agent.LocalChatHistory;
import org.agent.MedAgent.store.RedisChatMemoryStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ChatSessionServiceImpl implements ChatSessionService {
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private RedisChatMemoryStore redisChatMemoryStore;

    //将数据持久化至mongodb
    @Override
    public void Dump2Mongo(Long memoryId){
        //查redis
        List<ChatMessage> messages = redisChatMemoryStore.getMessages(memoryId);
        RedisChatHistory redisChatHistory = new RedisChatHistory();
        redisChatHistory.setMemoryId(memoryId);
        redisChatHistory.setMessages(ChatMessageSerializer.messagesToJson(messages));
        mongoTemplate.insert(redisChatHistory);
        System.out.println(memoryId+"has been dumped to mongodb.");
    }
}
