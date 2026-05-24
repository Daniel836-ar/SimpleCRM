package org.example.simplecrm.service;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.example.simplecrm.dto.PatchSellerDto;
import org.example.simplecrm.dto.PatchTransactionDto;
import org.example.simplecrm.dto.TransactionDto;
import org.example.simplecrm.model.Seller;
import org.example.simplecrm.model.Transaction;
import org.example.simplecrm.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
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
    @Transactional
    public void deleteById(Long id) throws Exception{
        if (!transactionRepository.findById(id).isEmpty()) {
            transactionRepository.deleteBySellerId(id);
        }else{
            throw new Exception();
        }

    }

    @Transactional
    public Transaction update(Long id, PatchTransactionDto dto){
        Transaction findTransaction = findById(id);
        if(findTransaction==null){// нет транзакции по этому id
            return null;
        }

        // проходимся по каждому полю , и обновляем при надобности
        if(dto.getAmount()!=null){
            findTransaction.setAmount(dto.getAmount());
        }

        if(dto.getPaymantType()!=null){
            findTransaction.setPaymantType(dto.getPaymantType());
        }

        if(dto.getTransactionDate()!=null){
            findTransaction.setTransactionDate(dto.getTransactionDate());
        }
        transactionRepository.save(findTransaction);

        return findTransaction;
    }


}
