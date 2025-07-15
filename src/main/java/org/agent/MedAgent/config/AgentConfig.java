package org.agent.MedAgent.config;

import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.loader.FileSystemDocumentLoader;
import dev.langchain4j.data.document.parser.TextDocumentParser;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.rag.content.Content;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;
import dev.langchain4j.rag.query.Query;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
import dev.langchain4j.store.embedding.inmemory.InMemoryEmbeddingStore;
import jakarta.annotation.ManagedBean;
import org.agent.MedAgent.store.MyEmbeddingStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.PathMatcher;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Configuration
public class AgentConfig {
    @Autowired
    private EmbeddingStore embeddingStore;
    @Autowired
    private EmbeddingModel embeddingModel;

//    @Bean
//    public ContentRetriever contentRetriever(){
//        //使用FileSystemDocumentLoader读取指定目录下的知识库文档
//        // 从一个目录中加载所有的.md文档
//        String ResourcePath = System.getProperty("user.dir")+"/src/main/resources/RAG/";
//        PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher("glob:*.md");
//        List<Document> documents = FileSystemDocumentLoader.loadDocuments(ResourcePath, pathMatcher, new TextDocumentParser());
//        System.out.println(documents.size()+"==========");
//        //使用内存向量存储
//        //InMemoryEmbeddingStore<TextSegment> embeddingStore = new InMemoryEmbeddingStore<>();
//        //使用默认的文档分割器
//        //EmbeddingStoreIngestor.ingest(documents, embeddingStore);
//        //从嵌入存储（EmbeddingStore）里检索和查询内容相关的信息
//        return EmbeddingStoreContentRetriever.from(embeddingStore);
//    }
}
