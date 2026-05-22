package org.example.simplecrm.service;

import org.example.simplecrm.dto.PatchSellerDto;
import org.example.simplecrm.dto.SellerDto;
import org.example.simplecrm.model.Seller;
import org.example.simplecrm.repository.SellerRepository;
import org.example.simplecrm.repository.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SellerServiceTest {

    @Mock
    private SellerRepository sellerRepository;
    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private SellerService sellerService;

    @Test
    void findById_WhenExists_ShouldReturnSeller() {
        Seller seller = new Seller();
        seller.setId(1L);
        when(sellerRepository.findById(1L))
                .thenReturn(Optional.of(seller));

        Seller result = sellerService.findById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    void findById_WhenNotExists_ShouldReturnNull() {
        when(sellerRepository.findById(1L))
                .thenReturn(Optional.empty());

        Seller result = sellerService.findById(1L);

        assertNull(result);
    }

    @Test
    void save_ShouldReturnSavedSeller() {
        SellerDto dto = new SellerDto();
        dto.setName("Иван");
        dto.setContactInfo("ivan@mail.com");
        dto.setRegistrationDate(LocalDateTime.now());

        Seller savedSeller = new Seller();
        savedSeller.setId(1L);
        savedSeller.setName("Иван");

        when(sellerRepository.save(any(Seller.class)))
                .thenReturn(savedSeller);

        Seller result = sellerService.save(dto);

        assertNotNull(result);
        assertEquals("Иван", result.getName());
    }

    @Test
    void update_WhenSellerExists_ShouldUpdateFields() {
        Seller existingSeller = new Seller();
        existingSeller.setId(1L);
        existingSeller.setName("Старое Имя");

        PatchSellerDto patchDto = new PatchSellerDto();
        patchDto.setName("Новое Имя");

        when(sellerRepository.findById(1L))
                .thenReturn(Optional.of(existingSeller));

        when(sellerRepository.save(any(Seller.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Seller result = sellerService.update(1L, patchDto);

        assertNotNull(result);
        assertEquals("Новое Имя", result.getName());
    }

    @Test
    void deleteById_WhenExists_ShouldDelete() throws Exception {
        Seller seller = new Seller();
        when(sellerRepository.findById(1L))
                .thenReturn(Optional.of(seller));

        assertDoesNotThrow(() -> sellerService.deleteById(1L));
        verify(sellerRepository, times(1))
                .deleteById(1L);
    }

    @Test
    void deleteById_WhenNotExists_ShouldThrowException() {
        when(sellerRepository.findById(1L))
                .thenReturn(Optional.empty());

        Exception exception = assertThrows(Exception.class, () -> sellerService.deleteById(1L));
        assertEquals("Не нашли продавца по данному id", exception.getMessage());
    }
}
