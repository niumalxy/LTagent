package org.agent.MedAgent.config;

import org.agent.MedAgent.constant.RedisKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope("prototype") //原型bean，用一次就不用
public class StartBeforeCheck {
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private MongoTemplate mongoTemplate;

    @Bean
    public void CheckRedisKey(){
        //检查redis队列键是否存在
        if(!redisTemplate.hasKey(RedisKey.QUEUE_KEY)){
            redisTemplate.opsForList().leftPush(RedisKey.QUEUE_KEY, "EMPTY");
        }
    }

    @Bean
    public void CheckMongodb(){
        List<String> target_collections = new ArrayList<>();
        target_collections.add("Chat");
        target_collections.add("Appointment");
        for(String collectionName : target_collections){
            if (!mongoTemplate.collectionExists(collectionName)) {
                mongoTemplate.createCollection(collectionName);
            }
        }
    }
}
