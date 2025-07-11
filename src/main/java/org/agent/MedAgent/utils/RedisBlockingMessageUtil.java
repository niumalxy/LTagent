package org.agent.MedAgent.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedisBlockingMessageUtil {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    //生产消息
    public void sendMessage(String QUEUE_KEY, String message){
        //先进先出
        stringRedisTemplate.opsForList().leftPush(QUEUE_KEY, message);
    }

    //消费消息
    public String receiveMessage(String QUEUE_KEY){
        while(true){
            BoundListOperations<String, String> boundListOperations = stringRedisTemplate.boundListOps(QUEUE_KEY);
            String message = boundListOperations.rightPop(10, TimeUnit.SECONDS);
            if(message!=null){
                return message;
            }
        }
    }
}
