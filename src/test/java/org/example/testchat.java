package org.example;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.openai.OpenAiChatModel;

class testchat {
    public static void main(String[] args) {
        ChatModel model = OpenAiChatModel.builder()
                .baseUrl("https://spark-api-open.xf-yun.com/v1/")
                .apiKey("YzoWIOeXktOzJlebzBfK:cjFMRMiMVwBzMGSBHjnb")
                .modelName("Lite")
                .build();
        String answer = model.chat("Say 'Hello World'");
        System.out.println(answer); // Hello World
    }
}