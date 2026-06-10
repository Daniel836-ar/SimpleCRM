package org.example.simplecrm.repository;

import org.example.simplecrm.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface SellerRepository extends JpaRepository<Seller, Long> {
    List<Seller> findByName(String name);
    Optional<Seller> findById(Long id);
    @Query("SELECT s FROM Transaction t JOIN t.seller s GROUP BY s.id HAVING SUM(t.amount) < :limitAmount")
    List<Seller> findSellersWithAmountSumLessThan(@Param("limitAmount") BigDecimal limitAmount);

    List<Seller> findByRegistrationDateBetween(LocalDateTime registrationDateAfter, LocalDateTime registrationDateBefore);

}
