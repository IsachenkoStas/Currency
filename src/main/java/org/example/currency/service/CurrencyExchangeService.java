package org.example.currency.service;

import org.example.currency.domain.CurrencyExchangeRates;

public interface CurrencyExchangeService {
    CurrencyExchangeRates getCurrencyExchangeRates(String base, String symbol);

    CurrencyExchangeRates getRates();
}