package org.example.simplecrm.repository;

import org.example.simplecrm.model.Seller;
import org.example.simplecrm.model.Transaction;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    // чтоб не было n+1
    @Query("SELECT tr FROM Transaction tr JOIN FETCH tr.seller s WHERE s.id = :id")
    List<Transaction> findBySellerId(@Param("id")Long sellerId);

    Optional<Transaction> findById(Long id);

    void deleteBySellerId(Long id);

    @Query("SELECT t.seller FROM Transaction t " +
            "WHERE t.transactionDate >= :start AND t.transactionDate <= :end " +
            "GROUP BY t.seller " +
            "ORDER BY SUM(t.amount) DESC")
    List<Seller> findTopSellerInPeriod(
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end,
            Pageable pageable
    );
}
