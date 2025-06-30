package org.agent.MedAgent.llm;

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import org.agent.MedAgent.config.LLMConfig;

public class llm {
    public static ChatLanguageModel model = OpenAiChatModel.builder()
            .baseUrl(LLMConfig.base_url)
            .apiKey(LLMConfig.api_key)
            .modelName(LLMConfig.model_name)
            .temperature(0.7)
            .build();

    public static String chat(String text) {
        return model.chat(text);
    }


}
