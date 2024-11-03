package de.group2.supermarket.repo;

import java.util.List;
import java.util.Optional;

import de.group2.supermarket.config.CustomMongorepository;
import de.group2.supermarket.entity.printer.Printer;

public interface PrinterRepository extends CustomMongorepository<Printer> {

    Optional<List<Printer>> findAllByType(String type);
    
}
