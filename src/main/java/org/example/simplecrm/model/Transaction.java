package org.example.simplecrm.model;

import jakarta.persistence.*;
import lombok.Data;
import org.example.simplecrm.model.enums.PaymantType;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
@Data
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Seller seller;

    private Double amount;
    @Enumerated(EnumType.STRING)
    private PaymantType paymantType;
    private LocalDateTime transactionDate;
}
