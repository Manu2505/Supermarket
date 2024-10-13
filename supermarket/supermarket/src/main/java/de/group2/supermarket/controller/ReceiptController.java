package de.group2.supermarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.NoSuchElementException;
import java.util.UUID;
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

import de.group2.supermarket.entity.Receipt;
import de.group2.supermarket.repo.ReceiptRepository;

@Controller
@RestController
@RequestMapping("/receipt")
public class ReceiptController {
    
    @Autowired
	private ReceiptRepository receiptRepository;

    @PostMapping("")
    public ResponseEntity<Object> add(@RequestBody Receipt receipt){
        return new ResponseEntity<Object>(receiptRepository.save(receipt), HttpStatus.CREATED); // Recap: 201 means "Created"
    }
    

    @GetMapping("") // localhost:8080/receipt
    public ResponseEntity<Object> getAll(){
        return new ResponseEntity<Object>(receiptRepository.findAll(), HttpStatus.OK); // Recap: 200 means "OK"
    }

    @GetMapping("{id}") // localhost:8080/receipt/"some id"
    public ResponseEntity<Object> getById(@PathVariable UUID id){
        try {
            return new ResponseEntity<Object>(receiptRepository.findById(id).get(), HttpStatus.OK);
        } catch (NoSuchElementException e){
            return new ResponseEntity<Object>("Receipt with the id " + id + " could not be found", HttpStatus.NOT_FOUND); // Recap: 404 means "Not found"
        }
    }

    @GetMapping("/type/{type}") // localhost:8080/receipt/date/"some name"
    public ResponseEntity<Object> getByName(@PathVariable String date){
        try {
            return new ResponseEntity<Object>(receiptRepository.findAllByDate(date).get(), HttpStatus.OK);
        } catch (NoSuchElementException e){
            return new ResponseEntity<Object>("Receipts with name " + date + " could not be found", HttpStatus.NOT_FOUND); 
        }
    }



    @DeleteMapping("/{id}") // localhost:8080/receipt/"some id"
    public ResponseEntity<Object> delete(@PathVariable UUID id){
        try{
        	receiptRepository.delete(receiptRepository.findById(id).get());
            return new ResponseEntity<Object>("Receipt with id " + id + " deleted", HttpStatus.OK);
        }catch (NoSuchElementException e){
            return new ResponseEntity<Object>("Receipt with id " + id + " could not be found", HttpStatus.NOT_FOUND);
        }
    }

    // delete mapping for whole categories??

    /*@DeleteMapping("/category/{category}") // localhost:8080/Receipt/category/"some category"
    public ResponseEntity<Object> delete(@PathVariable String category){
        try{
        	receiptRepository.delete(receiptRepository.findAllByCategory(category).get());
            return new ResponseEntity<Object>("Receipt with category " + category + " deleted", HttpStatus.OK);
        }catch (NoSuchElementException e){
            return new ResponseEntity<Object>("Receipt with category " + category + " could not be found", HttpStatus.NOT_FOUND);
        }
    }
    */
}
