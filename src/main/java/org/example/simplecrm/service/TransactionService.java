package org.example.simplecrm.service;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.example.simplecrm.dto.PatchTransactionDto;
import org.example.simplecrm.dto.TransactionDto;
import org.example.simplecrm.exceptions.BadRequestException;
import org.example.simplecrm.exceptions.NotFoundException;
import org.example.simplecrm.model.Seller;
import org.example.simplecrm.model.Transaction;
import org.example.simplecrm.repository.SellerRepository;
import org.example.simplecrm.repository.TransactionRepository;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final SellerRepository sellerRepository;

    public TransactionService(TransactionRepository transactionRepository, SellerRepository sellerRepository) {
        this.transactionRepository = transactionRepository;
        this.sellerRepository = sellerRepository;
    }

    public List<Transaction> findAll(){
        return transactionRepository.findAll();
    }

    public Transaction findById(Long id){
        Transaction findTransaction = transactionRepository.findById(id).orElse(null);
        if(findTransaction==null){
            throw new NotFoundException("Нет транзакции с таким id");
        }
        return findTransaction;
    }

    @Transactional
    public Transaction create(TransactionDto dto){
        Seller findSeller = sellerRepository.findById(dto.getSellerId()).orElse(null);
        if(findSeller==null){
            throw new BadRequestException("Не нашли продавца по данному id");
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
        // есть ли вообще такой продавец
        if (!sellerRepository.existsById(sellerId)) {
            throw new BadRequestException("Не нашли продавца по данному id");
        }
        List<Transaction> findTransactions = transactionRepository.findBySellerId(sellerId);
        return findTransactions;
    }
    @Transactional
    public void deleteById(Long id){
        if (!transactionRepository.findById(id).isEmpty()) {
            transactionRepository.deleteBySellerId(id);
        }else{
            throw new NotFoundException("Не нашли продавца по данному id");
        }

    }

    @Transactional
    public Transaction update(Long id, PatchTransactionDto dto){
        Transaction findTransaction = findById(id);
        if(findTransaction==null){// нет транзакции по этому id
            throw new NotFoundException("Нет транзакции с таким id");
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
