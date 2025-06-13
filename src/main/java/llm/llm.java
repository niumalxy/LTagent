package llm;


import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import config.LLMConfig;

public class llm {
    public static ChatModel model = OpenAiChatModel.builder()
            .baseUrl(LLMConfig.base_url)
            .apiKey(LLMConfig.api_key)
            .modelName(LLMConfig.model_name)
            .build();

    public static String chat(String text) {
        return model.chat(text);
    }


}
