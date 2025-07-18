package org.agent.MedAgent.Object;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("Archive")
public class Archive {
    private String name;
    private String idcard;
    private String disease;
    private String department;
    private String time;
}
