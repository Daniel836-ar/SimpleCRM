package org.example.simplecrm.service;

import org.example.simplecrm.model.Seller;
import org.example.simplecrm.repository.SellerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AnalyticsServiceTest {

    @Mock
    private SellerRepository sellerRepository;

    @InjectMocks
    private AnalyticsService analyticsService;

    @Test
    void getSellerWithSumAmountLessThan_ShouldReturnList() {
        BigDecimal amount = BigDecimal.valueOf(1000);
        List<Seller> expectedSellers = List.of(new Seller(), new Seller());

        when(sellerRepository.findSellersWithAmountSumLessThan(amount))
                .thenReturn(expectedSellers);

        List<Seller> actualSellers = analyticsService.getSellerWithSumAmountLessThan(amount);

        assertEquals(expectedSellers.size(), actualSellers.size());
    }
}
