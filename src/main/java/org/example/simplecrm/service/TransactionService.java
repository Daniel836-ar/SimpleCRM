package org.example.simplecrm.service;

import jakarta.transaction.Transactional;
import org.example.simplecrm.dto.TransactionDto;
import org.example.simplecrm.model.Seller;
import org.example.simplecrm.model.Transaction;
import org.example.simplecrm.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final SellerService sellerService;

    public TransactionService(TransactionRepository transactionRepository, SellerService sellerService) {
        this.transactionRepository = transactionRepository;
        this.sellerService = sellerService;
    }

    public List<Transaction> findAll(){
        return transactionRepository.findAll();
    }

    public Transaction findById(Long id){
        return transactionRepository.findById(id).orElse(null);
    }

    @Transactional
    public Transaction create(TransactionDto dto) throws Exception {
        Seller findSeller =sellerService.findById(dto.getSellerId());
        if(findSeller==null){
            throw new Exception("Не нашли продавца по данному id");
        }
        Transaction transaction = new Transaction();
        transaction.setSeller(findSeller);
        transaction.setAmount(dto.getAmount());
        transaction.setTransactionDate(dto.getTransactionDate());
        transaction.setPaymantType(dto.getPaymantType());

        transactionRepository.save(transaction);
        return transaction;
    }

    public List<Transaction> findBySeller(Long sellerId){
        return transactionRepository.findBySellerId(sellerId);
    }


}
