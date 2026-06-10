package org.example.simplecrm.controller;

import jakarta.validation.Valid;
import org.example.simplecrm.dto.PatchSellerDto;
import org.example.simplecrm.dto.SellerDto;
import org.example.simplecrm.exceptions.ExceptionNotFound;
import org.example.simplecrm.model.Seller;
import org.example.simplecrm.model.Transaction;
import org.example.simplecrm.service.SellerService;
import org.example.simplecrm.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sellers")
public class SellerController {
    private final SellerService sellerService;
    private final TransactionService transactionService;

    public SellerController(SellerService sellerService, TransactionService transactionService) {
        this.sellerService = sellerService;
        this.transactionService = transactionService;
    }

    @GetMapping
    public List<Seller> getSellers(@RequestParam (required = false)String name){
        if(name!=null && !name.trim().isEmpty()){//если передали name
            return sellerService.findByName(name);
        }
        return sellerService.findAll();
    }

    @GetMapping("/{id}")
    private ResponseEntity<Seller> getById(@PathVariable Long id){
        Seller find = sellerService.findById(id);
        if(find==null){
            return ResponseEntity.notFound().build();// выкидываю ошибку если null
        }
        return ResponseEntity.ok(find);

    }

    @GetMapping("/{id}/transactions")
    public ResponseEntity<List<Transaction>> getTransactions(@PathVariable Long id){
        List<Transaction> findTransasctions = transactionService.findBySeller(id);
        if(findTransasctions.size()!=0){
            return ResponseEntity.ok(findTransasctions);
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping
    public ResponseEntity<Seller> createSeller(@RequestBody @Valid SellerDto seller){
        Seller savedSeller =  sellerService.save(seller);
        return new ResponseEntity<>(savedSeller, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Seller> updateSeller(@PathVariable Long id, @RequestBody @Valid PatchSellerDto patchSellerDto){
    Seller updateSeller = sellerService.update(id, patchSellerDto);
    if (updateSeller==null){
        throw new ExceptionNotFound("Не смогли обновить данные продавца");
    }
    return ResponseEntity.ok(updateSeller);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteSeller(@PathVariable Long id){

        sellerService.deleteById(id);
        return ResponseEntity.noContent().build();

    }




}
