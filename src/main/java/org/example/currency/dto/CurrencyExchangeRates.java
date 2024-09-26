package org.example.currency.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyExchangeRates {
    private String base_code;
    private Map<String, Double> conversion_rates;
}