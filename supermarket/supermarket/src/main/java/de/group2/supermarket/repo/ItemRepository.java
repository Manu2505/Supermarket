package de.group2.supermarket.repo;

import java.util.Optional;
import java.util.List;
import de.group2.supermarket.config.CustomJpaRepository;
import de.group2.supermarket.entity.Item;

public interface ItemRepository extends CustomJpaRepository<Item> {

    Optional<List<Item>> findByName(String name);
    Optional<List<Item>> findAllByCategory(String category);
}
