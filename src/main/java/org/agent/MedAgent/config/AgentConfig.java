package org.agent.MedAgent.config;

import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.loader.FileSystemDocumentLoader;
import dev.langchain4j.data.document.parser.TextDocumentParser;
import dev.langchain4j.data.document.splitter.DocumentByParagraphSplitter;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
import jakarta.annotation.Resource;
import org.agent.MedAgent.bean.ConstrantRPSEmbeddingModel;
import org.agent.MedAgent.store.MyEmbeddingStore;
import org.agent.MedAgent.utils.LocalRemoteTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Configuration
public class AgentConfig {
    @Autowired
    private MyEmbeddingStore embeddingStore;
    @Resource
    private ConstrantRPSEmbeddingModel constrantRPSEmbeddingModel;
    @Value("${apikey}")
    private String api_key;

    @Bean
    public ContentRetriever contentRetriever(){

        //使用FileSystemDocumentLoader读取指定目录下的知识库文档
        // 从一个目录中加载所有的.md文档
        String ResourcePath = System.getProperty("user.dir")+"/src/main/resources/RAG/";
        PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher("glob:*.md");
        List<Document> documents = FileSystemDocumentLoader.loadDocuments(ResourcePath, pathMatcher, new TextDocumentParser());
        System.out.println("总文档个数:" + documents.size() + "==========");
        //使用默认的文档分割器
        //自定义文档分割器
        //按段落分割文档：每个片段包含不超过 300个token，并且有 30个token的重叠部分保证连贯性
        //注意：当段落长度总和小于设定的最大长度时，就不会有重叠的必要。
        //QwenTokenizer tokenizer = new QwenTokenizer(api_key, "qwen-max");
        LocalRemoteTokenizer tokenizer = new LocalRemoteTokenizer();
        DocumentByParagraphSplitter documentSplitter = new DocumentByParagraphSplitter(
                300,
                30,
                tokenizer);
        //防止RPS过高，分批索引文档
        EmbeddingStoreIngestor
                .builder()
                .embeddingStore(embeddingStore)
                .documentSplitter(documentSplitter)
                .embeddingModel(constrantRPSEmbeddingModel)
                .build()
                .ingest(documents);
        return EmbeddingStoreContentRetriever.builder()
                .embeddingStore(embeddingStore)
                .embeddingModel(constrantRPSEmbeddingModel)
                .build();
    }
}
