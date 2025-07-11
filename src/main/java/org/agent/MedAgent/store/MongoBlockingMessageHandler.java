package org.agent.MedAgent.store;

import org.agent.MedAgent.Service.ChatSessionService;
import org.agent.MedAgent.constant.RedisKey;
import org.agent.MedAgent.utils.RedisBlockingMessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

@Component
public class MongoBlockingMessageHandler {
    @Autowired
    private ThreadPoolTaskExecutor executor;
    @Autowired
    private ChatSessionService chatSessionService;
    @Autowired
    private RedisBlockingMessageUtil redisBlockingMessageUtil;

    public void StartHandlingMongoBlockingQueue(){
        executor.submit(() -> {
            System.out.println("startBlockingQueue.");
            while(true){
                try {
                    String memoryId = redisBlockingMessageUtil.receiveMessage(RedisKey.QUEUE_KEY);
                    System.out.println("get memoryId: " + memoryId);
                    chatSessionService.Dump2Mongo(Long.parseLong(memoryId));
                }
                catch (Exception e){
                    System.out.println("Blocking queue error happened");
                    break;
                }
            }
        });
    }
}
