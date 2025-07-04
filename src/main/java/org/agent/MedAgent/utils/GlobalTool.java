package org.agent.MedAgent.utils;

import org.agent.MedAgent.constant.RedisKey;

import java.util.Random;

public class GlobalTool {

    //生成MemoryId，使用时间戳+4位随机数
    public static Long MemoryIdGenerater(Long Id){
        //Long timestamp = System.currentTimeMillis();
        //将MemoryId补为9位 1为占位符
        String numStr = "1" + String.format("%08d", Id);
        //return Long.parseLong(timestamp.toString()+numStr);
        return Long.parseLong(numStr);
    }

    public static String MemoryId2RedisKey(Object memoryId){
        return RedisKey.ChatHistoryKey+String.valueOf(memoryId);
    }

}
