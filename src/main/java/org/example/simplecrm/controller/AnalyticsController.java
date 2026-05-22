package org.example.simplecrm.controller;

import org.example.simplecrm.model.Seller;
import org.example.simplecrm.service.AnalyticsService;
import org.example.simplecrm.service.TransactionService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/analystics")
public class AnalyticsController {
    private final AnalyticsService analyticsService;

    public AnalyticsController(AnalyticsService analyticsService) {
        this.analyticsService = analyticsService;
    }
    @GetMapping
    public List<Seller> getSellerWithSumAmountLessThen(@RequestParam BigDecimal amount){
        return analyticsService.getSellerWithSumAmountLessThen(amount);
    }
}
