package org.example.simplecrm.repository;

import org.example.simplecrm.model.Seller;
import org.example.simplecrm.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    // чтоб не было n+1
    @Query("SELECT tr FROM Transaction tr JOIN FETCH tr.seller s WHERE s.id = :id")
    List<Transaction> findBySellerId(@Param("id")Long sellerId);

    Optional<Transaction> findById(Long id);

    void deleteBySellerId(Long id);
}
