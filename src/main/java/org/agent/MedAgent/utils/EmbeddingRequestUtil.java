package org.agent.MedAgent.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.http.HttpClient;

@Configuration
public class EmbeddingRequestUtil {
    @Bean
    public HttpClient embeddingClient(){
        return HttpClient.newHttpClient();
    }
}
