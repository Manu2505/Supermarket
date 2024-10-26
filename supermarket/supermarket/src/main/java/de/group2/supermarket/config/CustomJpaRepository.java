package de.group2.supermarket.config;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface CustomJpaRepository<T extends UuidIdentifiedEntity> extends JpaRepository<T, UUID> {

}
