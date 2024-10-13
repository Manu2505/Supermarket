package de.group2.supermarket.repo;

import java.util.Optional;
import java.util.List;
import de.group2.supermarket.config.CustomMongoRepository;
import de.group2.supermarket.entity.Item;

public interface ItemRepository extends CustomMongoRepository<Item> {

    Item save(Item item);
    Optional<List<Item>> findAllByCategory(String category);
}
