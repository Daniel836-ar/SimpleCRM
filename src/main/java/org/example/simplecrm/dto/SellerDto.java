package org.example.simplecrm.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SellerDto {
    @NotBlank
    private String name;
    @NotBlank
    private String contactInfo;
    @NotNull
    private LocalDateTime registrationDate;
}
