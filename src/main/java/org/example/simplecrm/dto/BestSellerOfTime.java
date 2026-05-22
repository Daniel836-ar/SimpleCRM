package org.example.simplecrm.dto;

import lombok.Data;
import org.example.simplecrm.model.Seller;

@Data
public class BestSellerOfTime {
    private Seller BestOfDay;
    private Seller BestOfMonth;
    private Seller BestOfWeek;
    private Seller BestOfYear;
}
