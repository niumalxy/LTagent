package org.agent.MedAgent.Controller;

import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.store.embedding.EmbeddingSearchRequest;
import dev.langchain4j.store.embedding.EmbeddingSearchResult;
import dev.langchain4j.store.embedding.EmbeddingStore;
import org.agent.MedAgent.Object.RedisChatHistory;
import org.agent.MedAgent.agent.LocalChatHistory;
import org.agent.MedAgent.store.MyEmbeddingStore;
import org.agent.MedAgent.utils.GlobalTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private MyEmbeddingStore embeddingStore;
    @Autowired
    private EmbeddingModel embeddingModel;

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
        embeddingStore.add(embeddingModel.embed("你好").content(), TextSegment.from("你好"));
        embeddingStore.add(embeddingModel.embed("再见").content(), TextSegment.from("再见"));
        System.out.println("向量数据库中数据数量："+embeddingStore.getMapSize());
        //检索
        EmbeddingSearchResult<TextSegment> result = embeddingStore.search(new EmbeddingSearchRequest(embeddingModel.embed("您好").content(), 10, 0.8, null));
        System.out.println(result.matches().toString());

    }
}
