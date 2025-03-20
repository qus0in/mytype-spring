package org.example.mytype.model.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.cdimascio.dotenv.Dotenv;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.example.mytype.model.dto.AIRequest;
import org.example.mytype.model.dto.ChatResponse;
import org.springframework.stereotype.Repository;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.logging.Logger;

@Repository
public class AIRepository {
    Logger logger = Logger.getLogger(AIRepository.class.getName());
    Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
    HttpClient httpClient = HttpClient.newHttpClient();
    ObjectMapper objectMapper = new ObjectMapper();
    String baseUrl = dotenv.get("AI_API_URL");

    public <T> T useModel(String model, AIRequest param, Class<T> responseType) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("%s/%s".formatted(baseUrl, model)))
                .POST(HttpRequest.BodyPublishers.ofString(
                        objectMapper.writeValueAsString(param)))
                .headers("Content-Type", "application/json")
                .build();
        HttpResponse<String> response = httpClient.send(
                request, HttpResponse.BodyHandlers.ofString()
        );
        logger.info(response.body());
        if (response.statusCode() >= 400) {
            throw new RuntimeException(response.statusCode() + " " + response.body());
        }
        return objectMapper.readValue(response.body(), responseType);
    }
}