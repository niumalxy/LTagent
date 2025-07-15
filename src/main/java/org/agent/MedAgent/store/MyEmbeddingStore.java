package org.agent.MedAgent.store;

import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.store.embedding.EmbeddingMatch;
import dev.langchain4j.store.embedding.EmbeddingSearchRequest;
import dev.langchain4j.store.embedding.EmbeddingSearchResult;
import dev.langchain4j.store.embedding.EmbeddingStore;
import org.springframework.boot.autoconfigure.jms.artemis.ArtemisProperties;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class MyEmbeddingStore implements EmbeddingStore<TextSegment> {
    //TODO
    //后续可改成mongodb存储
    public Map<Embedding, TextSegment> docus;
    public MyEmbeddingStore(){
        this.docus = new HashMap<>();
    }

    public int getMapSize(){
        return this.docus.size();
    }

    @Override
    public String add(Embedding var){
        return "";
    }

    @Override
    public void add(String va1, Embedding emb){
        //this.docus.put(emb, text);
    }

    @Override
    public String add(Embedding emb, TextSegment text){
        this.docus.put(emb, text);
        return "success";
    }

    @Override
    public List<String> addAll(List<Embedding> var1){
        return new LinkedList<>();
    }

    @Override
    public void addAll(List<String> ids, List<Embedding> embeddings, List<TextSegment> embedded){
        if(embeddings.size() != embedded.size()){
            throw new IllegalArgumentException("Lists have different size!");
        }
        for(int i=0; i< embedded.size(); i++){
            this.docus.put(embeddings.get(i), embedded.get(i));
        }
    }

    @Override
    public EmbeddingSearchResult<TextSegment> search(EmbeddingSearchRequest req){
        //使用cos相似度匹配
        Embedding queryEmbedding = req.queryEmbedding();
        List<EmbeddingMatch<TextSegment>> result = new ArrayList<>();
        int id = 1;
        for(Map.Entry<Embedding, TextSegment> entry : this.docus.entrySet()){
            //计算相似度
            double score = cosineSimilarity(entry.getKey(), req.queryEmbedding());
            if(score > req.minScore()){
                EmbeddingMatch<TextSegment> match = new EmbeddingMatch<TextSegment>(score, String.valueOf(id++), entry.getKey(), entry.getValue());
                result.add(match);
            }
        }
        //排序并返回maxResult
        Collections.sort(result, (o1, o2) -> (o2.score() - o1.score())>0? 1:(o2.score() - o1.score())<0? -1:0);
        if(result.size()>req.maxResults()){
            return new EmbeddingSearchResult<TextSegment>(result.subList(0, req.maxResults()));
        }
        return new EmbeddingSearchResult<TextSegment>(result);
    }

    private double cosineSimilarity(Embedding e1, Embedding e2){

        double dotProduct = 0.0;
        double normA = 0.0;
        double normB = 0.0;
        for (int i = 0; i < e1.vector().length; i++) {
            dotProduct += e1.vector()[i] * e2.vector()[i];
            normA += Math.pow(e1.vector()[i], 2);
            normB += Math.pow(e2.vector()[i], 2);
        }
        normA = Math.sqrt(normA);
        normB = Math.sqrt(normB);
        return dotProduct / (normA * normB);
    }



}
