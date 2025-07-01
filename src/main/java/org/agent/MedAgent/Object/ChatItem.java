package org.agent.MedAgent.Object;

import lombok.Data;

@Data
public class ChatItem {
    private Long memoryId;
    private String message;

    public Long getMemoryId() {
        return memoryId;
    }

    public String getMessage() {
        return message;
    }
}
