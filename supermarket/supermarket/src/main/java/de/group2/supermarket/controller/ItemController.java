package de.group2.supermarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;
import java.util.UUID;

import de.group2.supermarket.entity.item.Item;
import de.group2.supermarket.entity.item.ItemBarcodePrinter;
import de.group2.supermarket.entity.item.ItemFactory;
import de.group2.supermarket.repo.ItemRepository;
import de.group2.supermarket.entity.logging.Logger; // Importiere deinen Logger


@Controller
@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemRepository itemRepository;

    @PostMapping("")
    public ResponseEntity<Object> add(@RequestBody Item item) {
        Logger logger = Logger.getInstance();
        Item newItem;
        try {
            if (item.isReduced()) {
                newItem = ItemFactory.createReducedTaxItem(item.getName(), item.getCategory(), item.getPrice());
            } else {
                newItem = ItemFactory.createStandardTaxItem(item.getName(), item.getCategory(), item.getPrice());
            }
            logger.log("Item hinzugefügt: " + newItem.getName() + ", Kategorie: " + newItem.getCategory());
            return new ResponseEntity<Object>(itemRepository.save(newItem), HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            logger.log("Fehler beim Hinzufügen eines Items: " + e.getMessage());
            return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("{id}") // localhost:8080/item/"some id"
    public ResponseEntity<Object> getById(@PathVariable UUID id) {
        Logger logger = Logger.getInstance();
        try {
            Item item = itemRepository.findById(id).get();
            logger.log("Item abgerufen: " + item.getName() + ", ID: " + id);
            return new ResponseEntity<Object>(item, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            logger.log("Item mit der ID " + id + " konnte nicht gefunden werden");
            return new ResponseEntity<Object>("Item with the id " + id + " could not be found", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}") // localhost:8080/item/"some id"
    public ResponseEntity<Object> updateById(@PathVariable UUID id, @RequestBody Item updatedItem) {
        Logger logger = Logger.getInstance();
        try {
            Item item = itemRepository.findById(id).get();
            // Hier kannst du das item aktualisieren
            logger.log("Item aktualisiert: " + item.getName() + ", ID: " + id);
            return new ResponseEntity<Object>(itemRepository.save(updatedItem), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            logger.log("Fehler beim Aktualisieren: Item mit der ID " + id + " konnte nicht gefunden werden");
            return new ResponseEntity<Object>("Item with the id " + id + " could not be found", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}") // localhost:8080/item/"some id"
    public ResponseEntity<Object> delete(@PathVariable UUID id) {
        Logger logger = Logger.getInstance();
        try {
            itemRepository.delete(itemRepository.findById(id).get());
            logger.log("Item gelöscht mit ID: " + id);
            return new ResponseEntity<Object>("Item with id " + id + " deleted", HttpStatus.OK);
        } catch (NoSuchElementException e) {
            logger.log("Fehler beim Löschen: Item mit der ID " + id + " konnte nicht gefunden werden");
            return new ResponseEntity<Object>("Item with id " + id + " could not be found", HttpStatus.NOT_FOUND);
        }
    }
}

