package org.agent.MedAgent.store;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.data.message.ChatMessageDeserializer;
import dev.langchain4j.data.message.ChatMessageSerializer;
import dev.langchain4j.store.memory.chat.ChatMemoryStore;
import org.agent.MedAgent.Object.RedisChatHistory;
import org.agent.MedAgent.utils.GlobalTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.LinkedList;
import java.util.List;

@Component
public class RedisChatMemoryStore implements ChatMemoryStore {
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public List<ChatMessage> getMessages(Object memoryId){
        String redis_key = GlobalTool.MemoryId2RedisKey(memoryId);
        String result = (String) redisTemplate.opsForValue().get(redis_key);
        if(result == null) {
            return new LinkedList<>();
        }
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            RedisChatHistory redisChatHistory = objectMapper.readValue(result, RedisChatHistory.class);
            return ChatMessageDeserializer.messagesFromJson(redisChatHistory.getMessages());
        } catch (JsonProcessingException e) {
            return new LinkedList<>();
        }
    }

    @Override
    public void updateMessages(Object memoryId, List<ChatMessage> messages){
        RedisChatHistory redisChatHistory = new RedisChatHistory();
        redisChatHistory.setMemoryId((Long)memoryId);
        redisChatHistory.setMessages(ChatMessageSerializer.messagesToJson(messages));
        ValueOperations valueOps = redisTemplate.opsForValue();
        String redis_key = GlobalTool.MemoryId2RedisKey(memoryId);
        //将对象序列化
        ObjectMapper objectMapper = new ObjectMapper();
        String json = null;
        try {
            json = objectMapper.writeValueAsString(redisChatHistory);
            valueOps.set(redis_key, json);
            //重置过期时间为3天
            redisTemplate.expire(redis_key, Duration.ofDays(60*60*72));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void deleteMessages(Object memoryId) {
        String redis_key = GlobalTool.MemoryId2RedisKey(memoryId);
        redisTemplate.delete(redis_key);
    }
}

