package org.example.simplecrm.controller;

import org.example.simplecrm.dto.BestSellerOfTime;
import org.example.simplecrm.model.Seller;
import org.example.simplecrm.service.AnalyticsService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/analytics")
public class AnalyticsController {
    private final AnalyticsService analyticsService;

    public AnalyticsController(AnalyticsService analyticsService) {
        this.analyticsService = analyticsService;
    }
    @GetMapping("/lessThen")
    public List<Seller> getSellerWithSumAmountLessThan(@RequestParam BigDecimal amount){
        return analyticsService.getSellerWithSumAmountLessThan(amount);
    }

    @GetMapping
    public BestSellerOfTime getBestSelersOfTime(){
        return analyticsService.getBestSelersOfTime();
    }




}
