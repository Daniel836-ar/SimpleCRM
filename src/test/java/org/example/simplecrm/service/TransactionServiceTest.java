package org.example.simplecrm.service;

import org.example.simplecrm.dto.PatchTransactionDto;
import org.example.simplecrm.dto.TransactionDto;
import org.example.simplecrm.model.Seller;
import org.example.simplecrm.model.Transaction;
import org.example.simplecrm.repository.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TransactionServiceTest {

    @Mock
    private TransactionRepository transactionRepository;
    @Mock
    private SellerService sellerService;

    @InjectMocks
    private TransactionService transactionService;

    @Test
    void create_WhenSellerExists_ShouldCreateTransaction() throws Exception {
        Seller seller = new Seller();
        seller.setId(1L);

        TransactionDto dto = new TransactionDto();
        dto.setSellerId(1L);
        dto.setAmount(BigDecimal.valueOf(500));

        when(sellerService.findById(1L)).thenReturn(seller);

        Transaction result = transactionService.create(dto);

        assertNotNull(result);
        assertEquals(seller, result.getSeller());
        assertEquals(BigDecimal.valueOf(500), result.getAmount());
        verify(transactionRepository, times(1)).save(any(Transaction.class));
    }

    @Test
    void create_WhenSellerNotExists_ShouldThrowException() {
        TransactionDto dto = new TransactionDto();
        dto.setSellerId(1L);

        when(sellerService.findById(1L)).thenReturn(null);

        Exception exception = assertThrows(Exception.class, () -> transactionService.create(dto));
        assertEquals("Не нашли продавца по данному id", exception.getMessage());
    }

    @Test
    void deleteById_WhenNotExists_ShouldThrowException() {
        when(transactionRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(Exception.class, () -> transactionService.deleteById(1L));
    }

    @Test
    void update_WhenTransactionExists_ShouldUpdateAmount() {
        Transaction existingTx = new Transaction();
        existingTx.setId(1L);
        existingTx.setAmount(BigDecimal.valueOf(100));

        PatchTransactionDto patchDto = new PatchTransactionDto();
        patchDto.setAmount(BigDecimal.valueOf(200));

        when(transactionRepository.findById(1L)).thenReturn(Optional.of(existingTx));

        Transaction result = transactionService.update(1L, patchDto);

        assertNotNull(result);
        assertEquals(BigDecimal.valueOf(200), result.getAmount());
    }
}
