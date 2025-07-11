package org.agent.MedAgent.constant;

public class RedisKey {
    public static final String ChatHistoryKey = "chat_history:";

    public static String formatChatHistoryKey(Long memoryId){
        return ChatHistoryKey+memoryId.toString();
    }

    //消息队列key
    public static final String QUEUE_KEY = "MongoQueue";
}
