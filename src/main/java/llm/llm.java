package llm;


import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.openai.OpenAiChatModel;

public class llm {
    public static ChatModel model = OpenAiChatModel.builder()
            .baseUrl("https://spark-api-open.xf-yun.com/v1/")
            .apiKey("YzoWIOeXktOzJlebzBfK:cjFMRMiMVwBzMGSBHjnb")
            .modelName("Lite")
            .build();

    public static String chat(String text) {
        return model.chat(text);
    }


}
