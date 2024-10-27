package de.group2.supermarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.Update;
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

import de.group2.supermarket.entity.Item;
import de.group2.supermarket.repo.ItemRepository;

@Controller
@RestController
@RequestMapping("/item")
public class ItemController {

@Autowired
	private ItemRepository itemRepository;

    @PostMapping("")
    public ResponseEntity<Object> add(@RequestBody Item item){
        return new ResponseEntity<Object>(itemRepository.save(item), HttpStatus.CREATED); // Recap: 201 means "Created"
    }
    

    @GetMapping("") // localhost:8080/item
    public ResponseEntity<Object> getAll(){
        return new ResponseEntity<Object>(itemRepository.findAll(), HttpStatus.OK); // Recap: 200 means "OK"
    }

    @GetMapping("{id}") // localhost:8080/item/"some id"
    public ResponseEntity<Object> getById(@PathVariable UUID id){
        try {
            return new ResponseEntity<Object>(itemRepository.findById(id).get(), HttpStatus.OK);
        } catch (NoSuchElementException e){
            return new ResponseEntity<Object>("Item with the id " + id + " could not be found", HttpStatus.NOT_FOUND); // Recap: 404 means "Not found"
        }
    }

    @GetMapping("/category/{category}") // localhost:8080/item/category/"some category"
    public ResponseEntity<Object> getByCategory(@PathVariable String category){
        try {
            return new ResponseEntity<Object>(itemRepository.findAllByCategory(category).get(), HttpStatus.OK);
        } catch (NoSuchElementException e){
            return new ResponseEntity<Object>("Items with name " + category + " could not be found", HttpStatus.NOT_FOUND); 
        }
    }   

    
    
    @GetMapping("/name/{name}") // localhost:8080/item/name/"some name"
    public ResponseEntity<Object> getByName(@PathVariable String name){
        try {
            return new ResponseEntity<Object>(itemRepository.findByName(name).get(), HttpStatus.OK);
        } catch (NoSuchElementException e){
            return new ResponseEntity<Object>("Items with name " + name + " could not be found", HttpStatus.NOT_FOUND); 
        }
    }

    @PutMapping("/{id}") // localhost:8080/item/"some id"
    public ResponseEntity<Object> updateById(@PathVariable UUID id) {
        try {
            return new ResponseEntity<Object>(itemRepository.findById(id).get(), HttpStatus.OK);
        } catch (NoSuchElementException e){
            return new ResponseEntity<Object>("Item with the id " + id + " could not be found", HttpStatus.NOT_FOUND); // Recap: 404 means "Not found"
        }
    }
    


    @DeleteMapping("/{id}") // localhost:8080/item/"some id"
    public ResponseEntity<Object> delete(@PathVariable UUID id){
        try{
        	itemRepository.delete(itemRepository.findById(id).get());
            return new ResponseEntity<Object>("Item with id " + id + " deleted", HttpStatus.OK);
        }catch (NoSuchElementException e){
            return new ResponseEntity<Object>("Item with id " + id + " could not be found", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/name/{name}") // localhost:8080/item/name/"some name"
    public ResponseEntity<Object> deleteByName(@PathVariable String name){
        try{
        	itemRepository.deleteByName(itemRepository.findByName(name).get());
            return new ResponseEntity<Object>("Item with name " + name + " deleted", HttpStatus.OK);
        }catch (NoSuchElementException e){
            return new ResponseEntity<Object>("Item with name " + name + " could not be found", HttpStatus.NOT_FOUND);
        }
    }
}
