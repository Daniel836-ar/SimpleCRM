package org.example.simplecrm.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SellerDto {
    @NotBlank
    @Size(min = 2)
    private String name;
    @NotBlank
    private String contactInfo;
    @NotNull
    private LocalDateTime registrationDate;
}
