package com.java1.java1.repository;

import com.java1.java1.entity.RepositoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RepositoryRepository extends JpaRepository<RepositoryEntity, Long>, JpaSpecificationExecutor<RepositoryEntity> {
}
