package org.example.currency.service;

import org.example.currency.DTO.CurrencyRateDto;
import org.example.currency.domain.CurrencyExchangeRates;

public interface CurrencyExchangeService {
    CurrencyRateDto getCurrencyExchangeRates(String base, String target);

    CurrencyExchangeRates getRatesOfBaseCurrency(String base);
}