package org.example.simplecrm.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "sellers")
@Data
public class Seller {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "contact_info", nullable = false)
    private String contactInfo;
    @Column(name = "registration_date", nullable = false)
    private LocalDateTime registrationDate;
}
