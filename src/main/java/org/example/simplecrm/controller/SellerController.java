package org.example.simplecrm.controller;

import jakarta.validation.Valid;
import org.example.simplecrm.dto.SellerDto;
import org.example.simplecrm.model.Seller;
import org.example.simplecrm.service.SellerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sellers")
public class SellerController {
    private final SellerService sellerService;

    public SellerController(SellerService sellerService) {
        this.sellerService = sellerService;
    }

    @GetMapping
    public List<Seller> getSellers(@RequestParam (required = false)String name){
        if(name!=null && !name.trim().isEmpty()){//если передали name
            return sellerService.findByName(name);
        }
        return sellerService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Seller> getById(@PathVariable Long id){
        Seller find = sellerService.findById(id);
        if(find==null){
            return ResponseEntity.notFound().build();// выкидываю ошибку если null
        }
        return ResponseEntity.ok(find);

    }
    @PostMapping
    public ResponseEntity<?> createSeller(@RequestBody @Valid SellerDto seller){
        Seller savedSeller =  sellerService.saveSeller(seller);
        return new ResponseEntity<>(savedSeller, HttpStatus.CREATED);
    }

}
