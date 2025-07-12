package com.java1.java1.controller;

import com.java1.java1.dto.SearchRequest;
import com.java1.java1.entity.RepositoryEntity;
import com.java1.java1.service.GitHubApiClient;
import com.java1.java1.service.RepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/github")
public class GitHubController {
    @Autowired
    private GitHubApiClient gitHubApiClient;
    @Autowired
    private RepositoryService repositoryService;

    @PostMapping("/search")
    public ResponseEntity<?> searchAndSave(@RequestBody SearchRequest request) {
        List<RepositoryEntity> repos = gitHubApiClient.searchRepositories(
                request.getQuery(), request.getLanguage(), request.getSort()
        );
        repositoryService.saveOrUpdateRepositories(repos);
        Map<String, Object> resp = new HashMap<>();
        resp.put("message", "Repositories fetched and saved successfully");
        resp.put("repositories", repos);
        return ResponseEntity.ok(resp);
    }

    @GetMapping("/repositories")
    public ResponseEntity<?> getRepositories(
            @RequestParam(required = false) String language,
            @RequestParam(required = false) Integer minStars,
            @RequestParam(required = false, defaultValue = "stars") String sort
    ) {
        List<RepositoryEntity> repos = repositoryService.findRepositories(language, minStars, sort);
        Map<String, Object> resp = new HashMap<>();
        resp.put("repositories", repos);
        return ResponseEntity.ok(resp);
    }
}
