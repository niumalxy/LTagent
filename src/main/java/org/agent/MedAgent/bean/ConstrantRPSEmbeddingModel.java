package org.agent.MedAgent.bean;

import com.google.gson.Gson;
import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.output.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Component("constrantRPSEmbeddingModel")
@Primary
public class ConstrantRPSEmbeddingModel implements EmbeddingModel {
    @Autowired
    private HttpClient httpClient;

    @Value("${apikey}")
    private String apikey;
    private final int wait_time = 1000;  //等待2s

    //重写方法，防止瞬间RPS过多
    @Override
    public Response<List<Embedding>> embedAll(List<TextSegment> texts){
        String base_url = "https://dashscope.aliyuncs.com/compatible-mode/v1/embeddings";
        String model = "text-embedding-v4";
        List<Embedding> result = new ArrayList<>();
        for(TextSegment text : texts) {
            Gson t = new Gson();
            String str = t.toJson(text.text());
            String json = String.format("{" +
                    "    \"model\": \"%s\"," +
                    "    \"input\": %s," +
                    "    \"dimension\": \"1024\"," +
                    "    \"encoding_format\": \"float\"" +
                    "}", model, str);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(base_url))
                    .header("Authorization", "Bearer " + apikey)
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .build();
            HttpClient client = HttpClient.newHttpClient();
            try {
                HttpResponse<String> res = client.send(request, HttpResponse.BodyHandlers.ofString());
                //HttpResponse<String> res = response.get();
                Gson gson = new Gson();
                ResponseObject responseObject = gson.fromJson(res.body(), ResponseObject.class);
                for (DataObject data : responseObject.data) {
                    result.add(Embedding.from(data.embedding));
                    System.out.println("文本检索结果：" + data.embedding);
                }
                Thread.sleep(wait_time);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return Response.from(result);
    }
    // 定义响应对象的内部类
    private static class ResponseObject {
        public List<DataObject> data;
        public String model;
        public String object;
        public UsageObject usage;
        public String id;
    }
    private static class DataObject {
        public List<Float> embedding;
        public int index;
        public String object;
    }
    private static class UsageObject {
        public int prompt_tokens;
        public int total_tokens;
    }
}
