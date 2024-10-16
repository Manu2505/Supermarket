package de.group2.supermarket.controller;

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

import java.util.NoSuchElementException;
import java.util.UUID;

import de.group2.supermarket.entity.Item;
import de.group2.supermarket.entity.ItemList;
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


    /*
    @GetMapping("/name/{name}") // localhost:8080/itemList/name/"some name"
    public ResponseEntity<Object> getByName(@PathVariable String name){
        try {
            return new ResponseEntity<Object>(itemListRepository.findAllByName(name).get(), HttpStatus.OK);
        } catch (NoSuchElementException e){
            return new ResponseEntity<Object>("ItemLists with name " + name + " could not be found", HttpStatus.NOT_FOUND); 
        }
    }   
    
    @GetMapping("/type/{type}") // localhost:8080/itemList/name/"some name"
    public ResponseEntity<Object> getByName(@PathVariable String category){
        try {
            return new ResponseEntity<Object>(itemListRepository.findAllByCategory(category).get(), HttpStatus.OK);
        } catch (NoSuchElementException e){
            return new ResponseEntity<Object>("ItemLists with name " + category + " could not be found", HttpStatus.NOT_FOUND); 
        }
    }
    */


    @DeleteMapping("/{id}") // localhost:8080/itemList/"some id"
    public ResponseEntity<Object> delete(@PathVariable UUID id){
        try{
        	itemListRepository.delete(itemListRepository.findById(id).get());
            return new ResponseEntity<Object>("ItemList with id " + id + " deleted", HttpStatus.OK);
        }catch (NoSuchElementException e){
            return new ResponseEntity<Object>("ItemList with id " + id + " could not be found", HttpStatus.NOT_FOUND);
        }
    }

    // delete mapping for whole categories??

    /*@DeleteMapping("/category/{category}") // localhost:8080/itemList/category/"some category"
    public ResponseEntity<Object> delete(@PathVariable String category){
        try{
        	itemListRepository.delete(itemListRepository.findAllByCategory(category).get());
            return new ResponseEntity<Object>("ItemList with category " + category + " deleted", HttpStatus.OK);
        }catch (NoSuchElementException e){
            return new ResponseEntity<Object>("ItemList with category " + category + " could not be found", HttpStatus.NOT_FOUND);
        }
    }
    */
}
