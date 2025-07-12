package com.java1.java1.entity;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "repositories")
public class RepositoryEntity {
    @Id
    private Long id;

    @Column(length = 255)
    private String name;

    @Column(columnDefinition = "TEXT") // Allow very long descriptions
    private String description;

    @Column(length = 255)
    private String owner;

    @Column(length = 255)
    private String language;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setStars(Integer stars) {
        this.stars = stars;
    }

    public void setForks(Integer forks) {
        this.forks = forks;
    }

    public void setLastUpdated(Instant lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getOwner() {
        return owner;
    }

    public String getLanguage() {
        return language;
    }

    public Integer getStars() {
        return stars;
    }

    public Integer getForks() {
        return forks;
    }

    public Instant getLastUpdated() {
        return lastUpdated;
    }

    private Integer stars;
    private Integer forks;

    @Column(name = "last_updated")
    private java.time.Instant lastUpdated;

    // getters and setters...
}
