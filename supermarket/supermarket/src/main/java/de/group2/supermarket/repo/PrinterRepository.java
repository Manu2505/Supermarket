package de.group2.supermarket.repo;

import java.util.List;
import java.util.Optional;

import de.group2.supermarket.config.CustomMongoRepository;
import de.group2.supermarket.entity.Printer;

public interface PrinterRepository extends CustomMongoRepository<Printer> {

    Optional<List<Printer>> findAllByType(String type);
    
}
