package de.group2.supermarket.repo;

import java.util.List;
import java.util.Optional;

import de.group2.supermarket.config.CustomMongorepository;
import de.group2.supermarket.entity.item.Item;

public interface ItemRepository extends CustomMongorepository<Item> {

    
    Optional<List<Item>> findByName(String name);
    Optional<List<Item>> findAllByCategory(String category);

}
