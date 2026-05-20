package org.example.simplecrm.controller;

import org.example.simplecrm.dto.TransactionDto;
import org.example.simplecrm.model.Transaction;
import org.example.simplecrm.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    public ResponseEntity<List<Transaction>> getTransactions(){
        return ResponseEntity.ok(
                transactionService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable Long id){
        Transaction findTrans = transactionService.findById(id);
        if(findTrans!=null){
            return ResponseEntity.ok(findTrans);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> createTransaction(@RequestBody TransactionDto dto){
        try{
            Transaction transaction = transactionService.create(dto);
            return new ResponseEntity<>(transaction, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
