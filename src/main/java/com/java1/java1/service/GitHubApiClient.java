package com.java1.java1.service;

import com.java1.java1.entity.RepositoryEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import com.fasterxml.jackson.databind.JsonNode;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Component
public class GitHubApiClient {
    private final RestTemplate restTemplate = new RestTemplate();

    public List<RepositoryEntity> searchRepositories(String query, String language, String sort) {
        String url = "https://api.github.com/search/repositories?q=" + query;
        if (language != null && !language.isEmpty()) url += "+language:" + language;
        if (sort != null && !sort.isEmpty()) url += "&sort=" + sort;

        ResponseEntity<JsonNode> response = restTemplate.getForEntity(url, JsonNode.class);
        JsonNode items = response.getBody().get("items");
        List<RepositoryEntity> repos = new ArrayList<>();
        if (items != null) {
            for (JsonNode item : items) {
                RepositoryEntity repo = new RepositoryEntity();
                repo.setId(item.get("id").asLong());
                repo.setName(item.get("name").asText());
                repo.setDescription(item.get("description").asText(""));
                repo.setOwner(item.get("owner").get("login").asText());
                repo.setLanguage(item.get("language").asText(""));
                repo.setStars(item.get("stargazers_count").asInt());
                repo.setForks(item.get("forks_count").asInt());
                repo.setLastUpdated(Instant.parse(item.get("updated_at").asText()));
                repos.add(repo);
            }
        }
        return repos;
    }
}
