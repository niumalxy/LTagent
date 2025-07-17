package org.agent.MedAgent.utils;

import ai.djl.huggingface.tokenizers.Encoding;
import com.google.gson.Gson;
import dev.langchain4j.data.message.*;
import dev.langchain4j.model.Tokenizer;
import io.swagger.v3.core.util.Json;
import org.agent.MedAgent.bean.ConstrantRPSEmbeddingModel;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class LocalRemoteTokenizer implements Tokenizer {
    private final static String base_url = "http://127.0.0.1:9999/tokenize";
    private final static HttpClient client = HttpClient.newHttpClient();

    public LocalRemoteTokenizer(){
    }

    @Override
    public int estimateTokenCountInText(String text) {
        Gson t = new Gson();
        text = t.toJson(text);
        try {
        String json = String.format("{\"text\": %s}", text);
        //System.out.println(json);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(base_url))
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .header("Content-Type", "application/json")
                .build();
        HttpResponse<String> res = client.send(request, HttpResponse.BodyHandlers.ofString());
        Gson gson = new Gson();
        ResponseObject responseObject = gson.fromJson(res.body(), ResponseObject.class);
        return responseObject.tokens.size();
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public int estimateTokenCountInMessage(ChatMessage message) {
        if (message instanceof SystemMessage systemMessage) {
            return this.estimateTokenCountInText(systemMessage.text());
        } else if (message instanceof UserMessage userMessage) {
            return this.estimateTokenCountInText(userMessage.singleText());
        } else if (message instanceof AiMessage aiMessage) {
            return this.estimateTokenCountInText(aiMessage.text());
        } else if (message instanceof ToolExecutionResultMessage toolExecutionResultMessage) {
            return this.estimateTokenCountInText(toolExecutionResultMessage.text());
        } else {
            throw new IllegalArgumentException("Unknown message type: " + String.valueOf(message));
        }
    }

    public int estimateTokenCountInMessages(Iterable<ChatMessage> messages) {
        int tokens = 0;
        for(ChatMessage message : messages) {
            tokens += this.estimateTokenCountInMessage(message);
        }
        return tokens;
    }

    // 定义响应对象的内部类
    private static class ResponseObject {
        public List<Long> tokens;
    }
}
