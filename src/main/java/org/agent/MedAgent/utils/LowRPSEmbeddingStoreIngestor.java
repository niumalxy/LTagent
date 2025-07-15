package org.agent.MedAgent.utils;

import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.DocumentSplitter;
import dev.langchain4j.data.document.DocumentTransformer;
import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.data.segment.TextSegmentTransformer;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.output.Response;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
import dev.langchain4j.store.embedding.IngestionResult;

import java.util.List;
import java.util.stream.Collectors;

public class LowRPSEmbeddingStoreIngestor extends EmbeddingStoreIngestor {
    public LowRPSEmbeddingStoreIngestor(DocumentTransformer documentTransformer, DocumentSplitter documentSplitter, TextSegmentTransformer textSegmentTransformer, EmbeddingModel embeddingModel, EmbeddingStore<TextSegment> embeddingStore) {
        super(documentTransformer, documentSplitter, textSegmentTransformer, embeddingModel, embeddingStore);
    }

//    @Override
//    public IngestionResult ingest(List<Document> documents) {
//        if (this.documentTransformer != null) {
//            documents = this.documentTransformer.transformAll(documents);
//        }
//
//        List<TextSegment> segments;
//        if (this.documentSplitter != null) {
//            segments = this.documentSplitter.splitAll(documents);
//            log.debug("Documents were split into {} text segments", segments.size());
//        } else {
//            segments = (List)documents.stream().map(Document::toTextSegment).collect(Collectors.toList());
//        }
//
//        if (this.textSegmentTransformer != null) {
//            segments = this.textSegmentTransformer.transformAll(segments);
//            log.debug("Text segments were transformed into {} text segments", documents.size());
//        }
//
//        log.debug("Starting to embed {} text segments", segments.size());
//        Response<List<Embedding>> embeddingsResponse = this.embeddingModel.embedAll(segments);
//        log.debug("Finished embedding {} text segments", segments.size());
//        log.debug("Starting to store {} text segments into the embedding store", segments.size());
//        this.embeddingStore.addAll((List)embeddingsResponse.content(), segments);
//        log.debug("Finished storing {} text segments into the embedding store", segments.size());
//        return new IngestionResult(embeddingsResponse.tokenUsage());
//    }
}
