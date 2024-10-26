package de.group2.supermarket.repo;

import java.util.Optional;
import java.util.List;
import de.group2.supermarket.config.CustomJpaRepository;
import de.group2.supermarket.entity.ItemList;

public interface ItemListRepository extends CustomJpaRepository<ItemList> {

    // Optional<List<Item>> findAllByName(String name);

    // Optional<List<Item>> findAllByCategory(String category);
}
