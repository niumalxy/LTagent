package org.agent.MedAgent.Object;

import dev.langchain4j.data.message.ChatMessage;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "Chat")
public class RedisChatHistory {
    @Id
    private Long memoryId;
    private String messages;

}
