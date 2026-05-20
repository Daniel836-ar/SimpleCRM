package org.example.simplecrm.dto;

import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PatchSellerDto {
    @Size(min = 2)
    private String name;
    private String contactInfo;
    private LocalDateTime registrationDate;
}
