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
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
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
        Query query = new Query(Criteria.where("memoryId").is(memoryId));
        if(mongoTemplate.find(query, RedisChatHistory.class).isEmpty()){
            RedisChatHistory redisChatHistory = new RedisChatHistory();
            redisChatHistory.setMemoryId(memoryId);
            redisChatHistory.setMessages(ChatMessageSerializer.messagesToJson(messages));
            mongoTemplate.insert(redisChatHistory, "Chat");
            return;
        }
        Update update = new Update().set("messages", ChatMessageSerializer.messagesToJson(messages));
        mongoTemplate.updateFirst(query, update, "Chat");
    }
}
