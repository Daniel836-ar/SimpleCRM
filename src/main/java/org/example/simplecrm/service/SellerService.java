package org.example.simplecrm.service;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.example.simplecrm.dto.PatchSellerDto;
import org.example.simplecrm.dto.SellerDto;
import org.example.simplecrm.model.Seller;
import org.example.simplecrm.repository.SellerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Slf4j
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

    @Transactional
    public Seller updateSeller(Long id, PatchSellerDto dto){
        Seller findSeller = findById(id);
        if(findSeller==null){// нет продавца по этому id
            return null;
        }

        // проходимся по каждому полю , чтобы понять какие обновлять
        if(dto.getName()!=null){
            findSeller.setName(dto.getName());
        }
        if(dto.getContactInfo()!=null){
            findSeller.setContactInfo(dto.getContactInfo());
        }
        if(dto.getRegistrationDate()!=null){
            findSeller.setRegistrationDate(dto.getRegistrationDate());
        }


        return sellerRepository.save(findSeller);
    }

    @Transactional
    public void deleteSellerById(Long id) throws Exception {

        if(findById(id)!=null) {
            sellerRepository.deleteById(id);
        }else {
            throw new Exception();
        }

    }
}
