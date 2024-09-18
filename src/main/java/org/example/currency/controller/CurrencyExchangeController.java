package org.example.currency.controller;

import lombok.RequiredArgsConstructor;
import org.example.currency.domain.CurrencyExchangeRates;
import org.example.currency.service.CurrencyExchangeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class CurrencyExchangeController {

    private final CurrencyExchangeService currencyExchangeService;

    @GetMapping("/exchange-rates-to-base")
    public ResponseEntity<CurrencyExchangeRates> getExchangeRates(@RequestParam String base, @RequestParam String symbol) {
        Optional<CurrencyExchangeRates> currencyExchangeRates = Optional.ofNullable(currencyExchangeService.getCurrencyExchangeRates(base, symbol));
        return currencyExchangeRates.map(ResponseEntity::ok)
                .orElseThrow();
    }

    @GetMapping("/rates")
    public ResponseEntity<CurrencyExchangeRates> getExchangeRates() {
        Optional<CurrencyExchangeRates> currencyExchangeRates = Optional.ofNullable(currencyExchangeService.getRates());
        return currencyExchangeRates.map(ResponseEntity::ok)
                .orElseThrow();
    }
}