package org.agent.MedAgent.tools;

import dev.langchain4j.agent.tool.P;
import dev.langchain4j.agent.tool.Tool;
import dev.langchain4j.agent.tool.ToolMemoryId;
import org.agent.MedAgent.Service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class AppointmentTool {
    @Autowired
    private AppointmentService appointmentService;

//    @Tool(name = "预约挂号", value = "根据参数，先执行工具方法queryDepartment查询是否可预约，并直接给用户回答是否可预约，并让用户确认"+
//                                    "所有信息，用户确认后再预约。如果用户没有提供具体的医生姓名，请从向量存储中找一位相关的医生。")
//    public void Appointment(@ToolMemoryId Long memoryId,
//                     @P(value = "infoMap", required = true)Map<String, String> info){
//
//    }
}
