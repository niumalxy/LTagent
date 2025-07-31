package org.agent.MedAgent.Controller;

import dev.langchain4j.memory.chat.ChatMemoryProvider;
import org.agent.MedAgent.Object.Archive;
import org.agent.MedAgent.Object.ChatItem;
import org.agent.MedAgent.Object.Response;
import org.agent.MedAgent.Object.Result;
import org.agent.MedAgent.Service.ArchiveService;
import org.agent.MedAgent.Service.ChatSessionService;
import org.agent.MedAgent.agent.LocalChatHistory;
import org.agent.MedAgent.agent.MedicalAgent;
import org.agent.MedAgent.utils.GlobalTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/chat")
public class ChatController {
    @Autowired
    private MedicalAgent medicalAgent;
    @Autowired
    private ChatMemoryProvider chatMemoryProvider;
    @Autowired
    private ChatSessionService chatSessionService;
    @Autowired
    private ArchiveService archiveService;

    @GetMapping("/check")
    public Result<Void> check(){
        return Response.success();
    }

    @PostMapping("/chat")
    public String chat(@RequestBody ChatItem chatItem)  {
        LocalDate now = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String currentDate = now.format(formatter);
        String history = chatSessionService.getHistory(chatItem.getIdcard());
        return medicalAgent.chat(GlobalTool.MemoryIdGenerater(chatItem.getMemoryId()), chatItem.getMessage(), currentDate, history);
    }

    /**
     * 根据身份证号查询历史病例
     * @param idcard
     * @return
     */
    @GetMapping("/archive/{idcard}")
    public List<Archive> get_archive(@PathVariable("idcard") String idcard){
        System.out.printf("获取idcard档案：%s", idcard);
        return archiveService.getArchiveByidcard(idcard);
    }

}
