package de.group2.supermarket.repo;

import java.util.Optional;
import java.util.List;
import de.group2.supermarket.config.CustomMongoRepository;
import de.group2.supermarket.entity.Receipt;

public interface ReceiptRepository extends CustomMongoRepository<Receipt> {
    
    Optional<List<Receipt>> findAllByDate(String date);
}
