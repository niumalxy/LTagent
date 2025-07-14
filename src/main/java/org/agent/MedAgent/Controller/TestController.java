package org.agent.MedAgent.Controller;

import org.agent.MedAgent.Object.RedisChatHistory;
import org.agent.MedAgent.utils.GlobalTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private MongoTemplate mongoTemplate;

    @GetMapping("/mongo")
    public void testMongo(){
        Long memoryId = 100000001L;
        String redis_key = GlobalTool.MemoryId2RedisKey(memoryId);
        String result = (String) redisTemplate.opsForValue().get(redis_key);
        RedisChatHistory redisChatHistory = new RedisChatHistory();
        redisChatHistory.setMemoryId(memoryId);
        redisChatHistory.setMessages(result);
        mongoTemplate.insert(redisChatHistory);
    }
}
