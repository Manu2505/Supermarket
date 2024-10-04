package de.group2.supermarket.config;

import java.util.UUID;

import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;

public class UuIdIdentifiedEntityEventListener extends AbstractMongoEventListener<UuidIdentifiedEntity> {
    
    @Override
    public void onBeforeConvert(BeforeConvertEvent<UuidIdentifiedEntity> event) {
        
        super.onBeforeConvert(event);
        UuidIdentifiedEntity entity = event.getSource();
        
        if(entity.getId() == null) {
            entity.setId(UUID.randomUUID());
        } 
    }
    
}
