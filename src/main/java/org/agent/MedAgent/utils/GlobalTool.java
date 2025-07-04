package org.agent.MedAgent.utils;

import java.util.Random;

public class GlobalTool {

    //生成MemoryId，使用时间戳+"_"+4位随机数
    public static String MemoryIdGenerater(){
        Long timestamp = System.currentTimeMillis();
        //四位随机数
        Random random = new Random();
        int num = random.nextInt(10000);
        String numStr = String.format("%04d", num);
        return timestamp.toString()+"_"+numStr;
    }
}
