package de.group2.supermarket.repo;

import java.util.List;
import java.util.Optional;

import de.group2.supermarket.config.CustomJpaRepository;
import de.group2.supermarket.entity.Printer;

public interface PrinterRepository extends CustomJpaRepository<Printer> {

    Optional<List<Printer>> findAllByType(String type);
    
}
