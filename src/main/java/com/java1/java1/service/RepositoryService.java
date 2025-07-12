package com.java1.java1.service;

import com.java1.java1.entity.RepositoryEntity;
import com.java1.java1.repository.RepositoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RepositoryService {
    @Autowired
    private RepositoryRepository repositoryRepository;

    public void saveOrUpdateRepositories(List<RepositoryEntity> repos) {
        repositoryRepository.saveAll(repos);
    }

    public List<RepositoryEntity> findRepositories(String language, Integer minStars, String sort) {
        Specification<RepositoryEntity> spec = Specification.where(null);

        if (language != null && !language.isEmpty()) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("language"), language));
        }
        if (minStars != null) {
            spec = spec.and((root, query, cb) -> cb.greaterThanOrEqualTo(root.get("stars"), minStars));
        }

        // Sorting
        org.springframework.data.domain.Sort s = org.springframework.data.domain.Sort.by("stars").descending();
        if ("forks".equalsIgnoreCase(sort)) {
            s = org.springframework.data.domain.Sort.by("forks").descending();
        } else if ("updated".equalsIgnoreCase(sort)) {
            s = org.springframework.data.domain.Sort.by("lastUpdated").descending();
        }

        return repositoryRepository.findAll(spec, s);
    }
}
