package org.agent.MedAgent.Service;


/**
 * @description:
 * 每个Session Chat的相关服务
 */
public interface ChatSessionService {
    //将对话存入MongoDB
    void Dump2Mongo(Long memoryId);
    String getHistory(Long idcard);
}
