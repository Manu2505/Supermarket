package de.group2.supermarket.config;

import java.util.List;
import java.util.UUID;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;

public class CustomJpaRepositoryImpl<T extends UuidIdentifiedEntity> extends SimpleJpaRepository<T, UUID> implements CustomJpaRepository<T> {

    @PersistenceContext
    private EntityManager entityManager;

    public CustomJpaRepositoryImpl(JpaEntityInformation<T, UUID> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public <S extends T> S save(S entity) {
        generateId(entity);
        return super.save(entity);
    }

    @Override
    public <S extends T> List<S> saveAll(Iterable<S> entities) {
        entities.forEach(this::generateId);
        return super.saveAll(entities);
    }

    protected <S extends T> void generateId(S entity) {
        if (entity != null && entity.getId() == null) {
            entity.setId(UUID.randomUUID());
        }
    }

}
