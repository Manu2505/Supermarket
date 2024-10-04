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

import de.group2.supermarket.entity.Printer;
import de.group2.supermarket.repo.PrinterRepository;


@Controller
@RestController
@RequestMapping("/addresses")
public class PrinterController {
	
	@Autowired
	private PrinterRepository printerRepository;

    @GetMapping("")
    public ResponseEntity<Object> getAll(){
        return new ResponseEntity<Object>(printerRepository.findAll(), HttpStatus.OK); // Recap: 200 means "OK"
    }

    @GetMapping("{id}")
    public ResponseEntity<Object> getById(@PathVariable UUID id){
        try {
            return new ResponseEntity<Object>(printerRepository.findById(id).get(), HttpStatus.OK);
        } catch (NoSuchElementException e){
            return new ResponseEntity<Object>("Address with the id " + id + " could not be found", HttpStatus.NOT_FOUND); // Recap: 404 means "Not found"
        }
    }

    @PostMapping("")
    public ResponseEntity<Object> add(@RequestBody Printer printer){
        return new ResponseEntity<Object>(printerRepository.save(printer), HttpStatus.CREATED); // Recap: 201 means "Created"
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable UUID id){
        try{
        	printerRepository.delete(printerRepository.findById(id).get());
            return new ResponseEntity<Object>("Address with id " + id + " deleted", HttpStatus.OK);
        }catch (NoSuchElementException e){
            return new ResponseEntity<Object>("Address with id " + id + " could not be found", HttpStatus.NOT_FOUND);
        }
    }

}
