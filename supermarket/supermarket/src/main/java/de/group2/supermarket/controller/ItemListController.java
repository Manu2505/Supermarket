package de.group2.supermarket.controller;

import java.util.NoSuchElementException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.group2.supermarket.entity.itemList.ItemList;
import de.group2.supermarket.entity.logging.Logger;
import de.group2.supermarket.repo.ItemListRepository;

@Controller
@RestController
@RequestMapping("/itemList")
public class ItemListController {

    @Autowired
    private ItemListRepository itemListRepository;

    @PostMapping("")
    public ResponseEntity<Object> add(@RequestBody ItemList itemList) {
        Logger logger = Logger.getInstance();
        logger.log("ItemList hinzugefügt");
        return new ResponseEntity<>(itemListRepository.save(itemList), HttpStatus.CREATED);
    }

    @GetMapping("{id}") // localhost:8080/itemList/"some id"
    public ResponseEntity<Object> getById(@PathVariable UUID id) {
        Logger logger = Logger.getInstance();
        try {
            ItemList itemList = itemListRepository.findById(id).get();
            logger.log("ItemList abgerufen");
            return new ResponseEntity<>(itemList, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            logger.log("ItemList mit der ID " + id + " konnte nicht gefunden werden");
            return new ResponseEntity<>("ItemList with the id " + id + " could not be found", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}") // localhost:8080/itemList/"some id"
    public ResponseEntity<Object> delete(@PathVariable UUID id) {
        Logger logger = Logger.getInstance();
        try {
            itemListRepository.delete(itemListRepository.findById(id).get());
            logger.log("ItemList gelöscht mit ID: " + id);
            return new ResponseEntity<>("ItemList with id " + id + " deleted", HttpStatus.OK);
        } catch (NoSuchElementException e) {
            logger.log("Fehler beim Löschen: ItemList mit der ID " + id + " konnte nicht gefunden werden");
            return new ResponseEntity<>("ItemList with id " + id + " could not be found", HttpStatus.NOT_FOUND);
        }
    }
}
