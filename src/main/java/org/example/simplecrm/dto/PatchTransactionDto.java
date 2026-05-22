package org.example.simplecrm.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.example.simplecrm.model.enums.PaymantType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Data
public class PatchTransactionDto {
    private BigDecimal amount;
    private PaymantType paymantType;
    private LocalDateTime transactionDate;
}
