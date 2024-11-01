package de.group2.supermarket.entity.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.group2.supermarket.repo.ItemRepository;

@Service
public class ItemService {
    
    @Autowired
    private ItemRepository itemRepository;
}
