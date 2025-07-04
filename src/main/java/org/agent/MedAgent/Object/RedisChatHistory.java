package org.agent.MedAgent.Object;

import lombok.Data;

@Data
public class RedisChatHistory {
    private Long memoryId;

    private String messages;

    public String getMessages() {
        return messages;
    }

    public void setMessages(String messages) {
        this.messages = messages;
    }

    public Long getMemoryId() {
        return memoryId;
    }

    public void setMemoryId(Long memoryId) {
        this.memoryId = memoryId;
    }
}
