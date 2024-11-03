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

import de.group2.supermarket.entity.receipt.Receipt;
import de.group2.supermarket.entity.receipt.ReceiptPrintJob;
import de.group2.supermarket.entity.receipt.Receipt.ReceiptBuilder;
import de.group2.supermarket.repo.ReceiptRepository;
import de.group2.supermarket.entity.logging.Logger;

@Controller
@RestController
@RequestMapping("/receipt")
public class ReceiptController {

    @Autowired
    private ReceiptRepository receiptRepository;

    @PostMapping("")
    public ResponseEntity<Object> add(@RequestBody Receipt receiptIn) {
        Logger logger = Logger.getInstance();
        Receipt receipt = new Receipt.ReceiptBuilder()
            .setDate(receiptIn.getDate())
            .setTime(receiptIn.getTime())
            .setCashier(receiptIn.getCashier())
            .setItemList(receiptIn.getItemList())
            .build();
        
        logger.log("Quittung hinzugefügt: " + receipt.getId());
        return new ResponseEntity<Object>(receiptRepository.save(receipt), HttpStatus.CREATED);
    }

    @GetMapping("{id}") // localhost:8080/receipt/"some id"
    public ResponseEntity<Object> getById(@PathVariable UUID id) {
        Logger logger = Logger.getInstance();
        try {
            Receipt receipt = receiptRepository.findById(id).get();
            logger.log("Quittung abgerufen: " + receipt.getId());
            return new ResponseEntity<Object>(receipt, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            logger.log("Quittung mit der ID " + id + " konnte nicht gefunden werden");
            return new ResponseEntity<Object>("Receipt with the id " + id + " could not be found", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}") // localhost:8080/receipt/"some id"
    public ResponseEntity<Object> delete(@PathVariable UUID id) {
        Logger logger = Logger.getInstance();
        try {
            receiptRepository.delete(receiptRepository.findById(id).get());
            logger.log("Quittung gelöscht mit ID: " + id);
            return new ResponseEntity<Object>("Receipt with id " + id + " deleted", HttpStatus.OK);
        } catch (NoSuchElementException e) {
            logger.log("Fehler beim Löschen: Quittung mit der ID " + id + " konnte nicht gefunden werden");
            return new ResponseEntity<Object>("Receipt with id " + id + " could not be found", HttpStatus.NOT_FOUND);
        }
    }
}
