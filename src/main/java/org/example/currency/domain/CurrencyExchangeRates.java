package org.example.currency.domain;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class CurrencyExchangeRates {
    private String base_code;
    private Map<String, Double> conversion_rates;
}
