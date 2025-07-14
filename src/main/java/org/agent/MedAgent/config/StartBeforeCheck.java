package org.agent.MedAgent.config;

import org.agent.MedAgent.constant.RedisKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class StartBeforeCheck {
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Bean
    public void CheckRedisKey(){
        //检查redis队列键是否存在
        if(!redisTemplate.hasKey(RedisKey.QUEUE_KEY)){
            redisTemplate.opsForList().leftPush(RedisKey.QUEUE_KEY, "EMPTY");
        }
    }
}
