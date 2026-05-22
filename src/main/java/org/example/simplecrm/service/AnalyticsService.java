package org.example.simplecrm.service;

import org.example.simplecrm.dto.BestSellerOfTime;
import org.example.simplecrm.model.Seller;
import org.example.simplecrm.repository.SellerRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AnalyticsService {
    private final SellerRepository sellerRepository;

    public AnalyticsService(SellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }

    public List<Seller> getSellerWithSumAmountLessThan(BigDecimal amount){
        return sellerRepository.findSellersWithAmountSumLessThan(amount);
    }


    public BestSellerOfTime getBestSelersOfTime(){
        LocalDateTime targetDay = LocalDateTime.of(2026,4,1,16,20);

        return new BestSellerOfTime();
    }
}
