package de.group2.supermarket.config;

import java.util.List;
import java.util.UUID;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.repository.query.MongoEntityInformation;
import org.springframework.data.mongodb.repository.support.SimpleMongoRepository;

public class CustomMongoRepositoryImpl<T extends UuidIdentifiedEntity> extends SimpleMongoRepository<T, UUID> implements CustomMongorepository<T> {

    public CustomMongoRepositoryImpl(MongoEntityInformation<T, UUID> metadata, MongoOperations mongoOperations) {
        
        super(metadata, mongoOperations);        
    }
    
    @SuppressWarnings("null")
    @Override
    public <S extends T> S save(S entity) {
        generateId(entity);
        return super.save(entity);
    }

    @SuppressWarnings("null")
    @Override
    public <S extends T> List<S> saveAll(Iterable<S> entities) {
        entities.forEach(entity -> generateId(entity));
        return super.saveAll(entities);
    }

    @SuppressWarnings("null")
    @Override
    public <S extends T> S insert(S entity) {
        generateId(entity);
        return super.insert(entity);
    }

    @SuppressWarnings("null")
    @Override
    public <S extends T> List<S> insert(Iterable<S> entities) {
        entities.forEach(entity -> generateId(entity));
        return super.insert(entities);
    }

    protected <S extends T> void generateId(S entity) {
        
        if(entity != null && entity.getId() == null) {
            entity.setId(UUID.randomUUID());
        }
    }

}
