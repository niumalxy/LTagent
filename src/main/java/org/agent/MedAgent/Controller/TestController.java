package org.agent.MedAgent.Controller;

import ai.djl.nn.core.Embedding;
import com.google.gson.Gson;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.embedding.onnx.HuggingFaceTokenizer;
import dev.langchain4j.store.embedding.EmbeddingSearchRequest;
import dev.langchain4j.store.embedding.EmbeddingSearchResult;
import dev.langchain4j.store.embedding.EmbeddingStore;
import jakarta.annotation.Resource;
import jakarta.annotation.Resources;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.agent.MedAgent.Object.RedisChatHistory;
import org.agent.MedAgent.agent.LocalChatHistory;
import org.agent.MedAgent.store.MyEmbeddingStore;
import org.agent.MedAgent.utils.GlobalTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private MyEmbeddingStore embeddingStore;
    @Resource
    private EmbeddingModel openAiEmbeddingModel;
    @Value("${apikey}")
    private String apikey;

    //测试本地变量存储的对话接口
    @GetMapping("/chat_history/{memoryId}")
    public void chat_init(@PathVariable Long memoryId) {
        System.out.println(LocalChatHistory.getMessage(memoryId));
    }

    @GetMapping("/mongo")
    public void testMongo(){
        Long memoryId = 100000001L;
        String redis_key = GlobalTool.MemoryId2RedisKey(memoryId);
        String result = (String) redisTemplate.opsForValue().get(redis_key);
        RedisChatHistory redisChatHistory = new RedisChatHistory();
        redisChatHistory.setMemoryId(memoryId);
        redisChatHistory.setMessages(result);
        mongoTemplate.insert(redisChatHistory);
    }

    @GetMapping("/embeddingstore")
    public void testembeddingstore(){
        embeddingStore.add(openAiEmbeddingModel.embed("你好").content(), TextSegment.from("你好"));
        embeddingStore.add(openAiEmbeddingModel.embed("再见").content(), TextSegment.from("再见"));
        System.out.println("向量数据库中数据数量："+embeddingStore.getMapSize());
        //检索
        EmbeddingSearchResult<TextSegment> result = embeddingStore.search(new EmbeddingSearchRequest(openAiEmbeddingModel.embed("您好").content(), 10, 0.8, null));
        System.out.println(result.matches().toString());

    }

    @GetMapping("/embed")
    public void testembed() throws ExecutionException, InterruptedException {
        String base_url = "https://dashscope.aliyuncs.com/compatible-mode/v1/embeddings";
        String model = "text-embedding-v4";
        List<Embedding> results = new ArrayList<>();
        List<TextSegment> texts = new ArrayList<>();
        texts.add(TextSegment.from("你好！"));
        texts.add(TextSegment.from("再见！"));
        for (TextSegment text : texts) {
            String json = String.format("{" +
                    "    \"model\": \"%s\"," +
                    "    \"input\": \"%s\"," +
                    "    \"dimension\": \"1024\"," +
                    "    \"encoding_format\": \"float\"" +
                    "}", model, text.text());
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(base_url))
                    .header("Authorization", "Bearer " + apikey)
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .build();
            HttpClient client = HttpClient.newHttpClient();
            CompletableFuture<HttpResponse<String>> response = client.sendAsync(request, HttpResponse.BodyHandlers.ofString());
            HttpResponse<String> result = response.get();
            Gson gson = new Gson();
            ResponseObject responseObject = gson.fromJson(result.body(), ResponseObject.class);
            for (DataObject data : responseObject.data) {
                System.out.println(data.embedding);
            }
        }
    }

    @GetMapping("/tokenizer")
    public void tokenizer(){
        HuggingFaceTokenizer huggingFaceTokenizer = new HuggingFaceTokenizer();
        System.out.println(huggingFaceTokenizer.getClass().getResourceAsStream("/bert-tokenizer.json"));
    }

    // 定义响应对象的内部类
    private static class ResponseObject {
        public List<DataObject> data;
        public String model;
        public String object;
        public UsageObject usage;
        public String id;
    }
    public static class DataObject {
        public List<Double> embedding;
        public int index;
        public String object;
    }
    public static class UsageObject {
        public int prompt_tokens;
        public int total_tokens;
    }

}
