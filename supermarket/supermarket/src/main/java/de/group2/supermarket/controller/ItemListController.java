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

import de.group2.supermarket.entity.itemList.ItemList;
import de.group2.supermarket.repo.ItemListRepository;

@Controller
@RestController
@RequestMapping("/itemList")
public class ItemListController {

@Autowired
	private ItemListRepository itemListRepository;

    @PostMapping("")
    public ResponseEntity<Object> add(@RequestBody ItemList itemList){
        return new ResponseEntity<Object>(itemListRepository.save(itemList), HttpStatus.CREATED); // Recap: 201 means "Created"
    }

    @GetMapping("") // localhost:8080/itemList
    public ResponseEntity<Object> getAll(){
        return new ResponseEntity<Object>(itemListRepository.findAll(), HttpStatus.OK); // Recap: 200 means "OK"
    }

    @GetMapping("{id}") // localhost:8080/itemList/"some id"
    public ResponseEntity<Object> getById(@PathVariable UUID id){
        try {
            return new ResponseEntity<Object>(itemListRepository.findById(id).get(), HttpStatus.OK);
        } catch (NoSuchElementException e){
            return new ResponseEntity<Object>("ItemList with the id " + id + " could not be found", HttpStatus.NOT_FOUND); // Recap: 404 means "Not found"
        }
    }
 
    
    //auch da keine Ahnung ob das für Update passt
    @PutMapping("/{id}") // localhost:8080/itemList/"some id"
    public ResponseEntity<Object> update(@PathVariable UUID id, @RequestBody ItemList itemList){
        try{
            itemList.setId(id);
            return new ResponseEntity<Object>(itemListRepository.save(itemList), HttpStatus.OK);
        }catch (NoSuchElementException e){
            return new ResponseEntity<Object>("ItemList with id " + id + " could not be found", HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/{id}") // localhost:8080/itemList/"some id"
    public ResponseEntity<Object> delete(@PathVariable UUID id){
        try{
        	itemListRepository.delete(itemListRepository.findById(id).get());
            return new ResponseEntity<Object>("ItemList with id " + id + " deleted", HttpStatus.OK);
        }catch (NoSuchElementException e){
            return new ResponseEntity<Object>("ItemList with id " + id + " could not be found", HttpStatus.NOT_FOUND);
        }
    }

}
