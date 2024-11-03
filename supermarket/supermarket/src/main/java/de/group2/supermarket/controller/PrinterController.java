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

import de.group2.supermarket.entity.printer.Printer;
import de.group2.supermarket.repo.PrinterRepository;


@Controller
@RestController
@RequestMapping("/printer")
public class PrinterController {
	
	@Autowired
	private PrinterRepository printerRepository;
    
    
        @PostMapping("")
        public ResponseEntity<Object> add(@RequestBody Printer printer){
            return new ResponseEntity<Object>(printerRepository.save(printer), HttpStatus.CREATED); // Recap: 201 means "Created"
        }

    @GetMapping("") // localhost:8080/printer
    public ResponseEntity<Object> getAll(){
        return new ResponseEntity<Object>(printerRepository.findAll(), HttpStatus.OK); // Recap: 200 means "OK"
    }

    @GetMapping("{id}") // localhost:8080/printer/"some id"
    public ResponseEntity<Object> getById(@PathVariable UUID id){
        try {
            return new ResponseEntity<Object>(printerRepository.findById(id).get(), HttpStatus.OK);
        } catch (NoSuchElementException e){
            return new ResponseEntity<Object>("Printer with the id " + id + " could not be found", HttpStatus.NOT_FOUND); // Recap: 404 means "Not found"
        }
    }

    @GetMapping("/type/{type}") // localhost:8080/printer/type/"some type"
    public ResponseEntity<Object> getByName(@PathVariable String type){
        try {
            return new ResponseEntity<Object>(printerRepository.findAllByType(type).get(), HttpStatus.OK);
        } catch (NoSuchElementException e){
            return new ResponseEntity<Object>("Building with name " + type + " could not be found", HttpStatus.NOT_FOUND); 
        }
    }

    

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable UUID id){
        try{
        	printerRepository.delete(printerRepository.findById(id).get());
            return new ResponseEntity<Object>("Printer with id " + id + " deleted", HttpStatus.OK);
        }catch (NoSuchElementException e){
            return new ResponseEntity<Object>("Printer with id " + id + " could not be found", HttpStatus.NOT_FOUND);
        }
    }

}
