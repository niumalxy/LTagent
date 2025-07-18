package org.agent.MedAgent.Object;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
public class ChatItem {
    @Id
    private Long memoryId;
    private String message;
    private Long idcard = -1L;
}
