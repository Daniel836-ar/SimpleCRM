package org.example.simplecrm.service;

import org.example.simplecrm.model.Seller;
import org.example.simplecrm.repository.SellerRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class AnalyticsService {
    private final SellerRepository sellerRepository;

    public AnalyticsService(SellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }

}
