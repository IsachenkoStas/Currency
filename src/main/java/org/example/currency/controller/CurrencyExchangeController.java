package org.example.currency.controller;

import lombok.RequiredArgsConstructor;
import org.example.currency.domain.CurrencyExchangeRates;
import org.example.currency.service.CurrencyExchangeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CurrencyExchangeController {

    private final CurrencyExchangeService currencyExchangeService;

    @GetMapping("/exchange-rates")
    public CurrencyExchangeRates getExchangeRates() {
        return currencyExchangeService.getCurrencyExchangeRates();
    }
}