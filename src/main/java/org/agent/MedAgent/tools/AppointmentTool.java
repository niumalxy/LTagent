package org.agent.MedAgent.tools;

import dev.langchain4j.agent.tool.P;
import dev.langchain4j.agent.tool.Tool;
import dev.langchain4j.agent.tool.ToolMemoryId;

import java.util.Map;

public class AppointmentTool {
    @Tool(name = "预约挂号", value = "在用户提供完整预约信息后调用，")
    public void appointment(@ToolMemoryId Long memoryId,
                     @P(value = "infoMap", required = true)Map<String, String> info){

    }
}
