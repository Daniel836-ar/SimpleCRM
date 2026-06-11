package org.example.simplecrm.service;

import org.example.simplecrm.dto.BestSellerOfTime;
import org.example.simplecrm.model.Seller;
import org.example.simplecrm.repository.SellerRepository;
import org.example.simplecrm.repository.TransactionRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.IsoFields;
import java.util.List;

@Service
public class AnalyticsService {
    private final SellerRepository sellerRepository;
    private final TransactionRepository transactionRepository;

    public AnalyticsService(SellerRepository sellerRepository, TransactionRepository transactionRepository) {
        this.sellerRepository = sellerRepository;
        this.transactionRepository = transactionRepository;
    }

    public List<Seller> getSellerWithSumAmountLessThan(BigDecimal amount){
        return sellerRepository.findSellersWithAmountSumLessThan(amount);
    }

    // Получение лучших продавцов за все периоды
    public BestSellerOfTime getBestSelersOfTime(){
        BestSellerOfTime bestSellerOfTime = new BestSellerOfTime();
        LocalDate today = LocalDate.now();

        // Заполняем все поля DTO текущими лучшими продавцами
        bestSellerOfTime.setBestOfDay(getBestSellerOfDay(today));
        bestSellerOfTime.setBestOfMonth(getBestSellerOfMonth(today));
        bestSellerOfTime.setBestOfQuarter(getBestSellerOfQuarter(today));
        bestSellerOfTime.setBestOfYear(getBestSellerOfYear(today));
        return bestSellerOfTime;
    }

    // Вспомогательный метод для получения ТОП-1
    private Seller getTopSeller(LocalDateTime start, LocalDateTime end) {
        Pageable limitOne = PageRequest.of(0, 1);
        List<Seller> result = transactionRepository.findTopSellerInPeriod(start, end, limitOne);
        return result.isEmpty() ? null : result.get(0);
    }

    // 1. Лучший продавец за день
    public Seller getBestSellerOfDay(LocalDate date) {
        LocalDateTime start = date.atStartOfDay();
        LocalDateTime end = date.atTime(LocalTime.MAX);
        return getTopSeller(start, end);
    }

    // 2. Лучший за месяц
    public Seller getBestSellerOfMonth(LocalDate date) {
        LocalDateTime start = date.withDayOfMonth(1).atStartOfDay();
        LocalDateTime end = date.withDayOfMonth(date.lengthOfMonth()).atTime(LocalTime.MAX);
        return getTopSeller(start, end);
    }

    // 3. Лучший за квартал
    public Seller getBestSellerOfQuarter(LocalDate date) {
        int quarter = date.get(IsoFields.QUARTER_OF_YEAR);
        int startMonth = (quarter - 1) * 3 + 1;

        LocalDate firstDayOfQuarter = LocalDate.of(date.getYear(), startMonth, 1);
        LocalDate lastDayOfQuarter = firstDayOfQuarter.plusMonths(2).withDayOfMonth(firstDayOfQuarter.plusMonths(2).lengthOfMonth());

        return getTopSeller(firstDayOfQuarter.atStartOfDay(), lastDayOfQuarter.atTime(LocalTime.MAX));
    }

    // 4. Лучший за год
    public Seller getBestSellerOfYear(LocalDate date) {
        LocalDateTime start = date.withDayOfYear(1).atStartOfDay();
        LocalDateTime end = LocalDate.of(date.getYear(), 12, 31).atTime(LocalTime.MAX);
        return getTopSeller(start, end);
    }
}
