package de.group2.supermarket.config;

import java.util.UUID;
import javax.persistence.PrePersist;

public class UuIdIdentifiedEntityEventListener {

    @PrePersist
    public void setUuid(UuidIdentifiedEntity entity) {
        if (entity.getId() == null) {
            entity.setId(UUID.randomUUID());
        }
    }
}
