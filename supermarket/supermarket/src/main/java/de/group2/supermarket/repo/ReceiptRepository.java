package de.group2.supermarket.repo;

import java.util.Optional;
import java.util.List;
import de.group2.supermarket.config.CustomJpaRepository;
import de.group2.supermarket.entity.Receipt;

public interface ReceiptRepository extends CustomJpaRepository<Receipt> {
    
    Optional<List<Receipt>> findAllByDate(String date);
}
