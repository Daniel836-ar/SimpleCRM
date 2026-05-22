package org.example.simplecrm.dto;

import lombok.Data;
import org.example.simplecrm.model.enums.PaymantType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Data
public class TransactionDto {
    private Long sellerId;
    private BigDecimal amount;
    private PaymantType paymantType;
    private LocalDateTime transactionDate;
}
