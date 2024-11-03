package de.group2.supermarket.config;

import java.security.SecureRandom;
import java.util.UUID;

import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;

public class UuIdIdentifiedEntityEventListener extends AbstractMongoEventListener<UuidIdentifiedEntity> {
    
    private static final SecureRandom random = new SecureRandom();

    @SuppressWarnings("null")
    @Override
    public void onBeforeConvert( BeforeConvertEvent<UuidIdentifiedEntity> event) {
        
        super.onBeforeConvert(event);
        UuidIdentifiedEntity entity = event.getSource();
        
        if(entity.getId() == null) {
            entity.setId(generateNumericUUID());
        } 

    }

    public static UUID generateNumericUUID() {
        // Ensure the first digit is between 1 and 9
        int firstDigit = random.nextInt(9) + 1;

        // Generate the remaining 12 digits
        long remainingDigits = (long) (random.nextDouble() * 1_000_000_000_000L);

        // Combine the first digit with the remaining digits
        return UUID.nameUUIDFromBytes((firstDigit + String.format("%012d", remainingDigits)).getBytes());
    }

    
}
