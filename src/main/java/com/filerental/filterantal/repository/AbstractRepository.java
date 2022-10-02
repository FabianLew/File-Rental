package com.filerental.filterantal.repository;

import com.filerental.filterantal.model.AbstractEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.UUID;

@NoRepositoryBean
public interface AbstractRepository<T extends AbstractEntity> extends JpaRepository<T, UUID> {
    T findByUuid(UUID uuid);
}
