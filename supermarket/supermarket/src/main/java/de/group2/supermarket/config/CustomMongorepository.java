package de.group2.supermarket.config;

import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface CustomMongoRepository<T extends UuidIdentifiedEntity> extends MongoRepository<T, UUID> {

}
