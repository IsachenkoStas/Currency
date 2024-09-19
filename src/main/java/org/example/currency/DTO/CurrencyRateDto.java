package org.example.currency.DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CurrencyRateDto {
    private String base_code;
    private String target_code;
    private Double conversion_rate;

}