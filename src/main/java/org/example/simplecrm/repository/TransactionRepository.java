package org.example.simplecrm.repository;

import org.example.simplecrm.model.Seller;
import org.example.simplecrm.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findBySellerId(Long sellerId);
    List<Transaction> findAllBySellerId(Long sellerId);
    Optional<Transaction> findById(Long id);
}
