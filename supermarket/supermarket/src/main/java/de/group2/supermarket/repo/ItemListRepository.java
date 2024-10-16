package de.group2.supermarket.repo;

import java.util.Optional;
import java.util.List;
import de.group2.supermarket.config.CustomMongorepository;
import de.group2.supermarket.entity.ItemList;

public interface ItemListRepository extends CustomMongorepository<ItemList> {

    
    //Optional<List<Item>> findAllByName(String name);

    // Optional<List<Item>> findAllByCategory(String category);
}
