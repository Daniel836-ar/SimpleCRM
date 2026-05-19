package org.example.simplecrm.service;

import org.example.simplecrm.dto.SellerDto;
import org.example.simplecrm.model.Seller;
import org.example.simplecrm.repository.SellerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SellerService {
    private final SellerRepository sellerRepository;

    public SellerService(SellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }
    public List<Seller> findByName(String name){
        return sellerRepository.findByName(name);
    }
    public List<Seller> findAll(){
        return sellerRepository.findAll();
    }
    public Seller findById(Long id){
        return sellerRepository.findById(id).orElse(null);
    }
    public Seller saveSeller(SellerDto sellerDto){
        Seller sellerSaving = new Seller();
        sellerSaving.setName(sellerDto.getName());
        sellerSaving.setContactInfo(sellerDto.getContactInfo());
        sellerSaving.setRegistrationDate(sellerDto.getRegistrationDate());

        return sellerRepository.save(sellerSaving);
    }
}
