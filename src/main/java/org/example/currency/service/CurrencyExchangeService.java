package org.example.currency.service;

import org.example.currency.domain.Currency;
import org.example.currency.dto.CurrencyDto;
import org.example.currency.dto.CurrencyRateDto;
import org.example.currency.dto.CurrencyExchangeRates;

import java.util.List;

public interface CurrencyExchangeService {
    CurrencyRateDto getCurrencyExchangeRates(String base, String target);

    void addNewCurrency(CurrencyDto currencyDto);

    List<Currency> getAllCurrencies();

    CurrencyExchangeRates getFilteredExchangeRates(String base);
}