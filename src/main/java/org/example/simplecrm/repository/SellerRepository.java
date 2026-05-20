package org.example.simplecrm.repository;

import org.example.simplecrm.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SellerRepository extends JpaRepository<Seller, Long> {
    List<Seller> findByName(String name);
    Optional<Seller> findById(Long id);
}
