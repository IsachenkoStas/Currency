package org.example.currency.controller;

import lombok.RequiredArgsConstructor;
import org.example.currency.DTO.CurrencyRateDto;
import org.example.currency.domain.CurrencyExchangeRates;
import org.example.currency.service.CurrencyExchangeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class CurrencyExchangeController {

    private final CurrencyExchangeService currencyExchangeService;

    @GetMapping("/exchange-rates-from/{base}/{target}")
    public ResponseEntity<CurrencyRateDto> getExchangeRates(@PathVariable String base, @PathVariable String target) {
        Optional<CurrencyRateDto> currencyExchangeRates = Optional.ofNullable(currencyExchangeService.getCurrencyExchangeRates(base, target));
        return currencyExchangeRates.map(ResponseEntity::ok)
                .orElseThrow();
    }

    @GetMapping("/rates/{base}")
    public ResponseEntity<CurrencyExchangeRates> getExchangeRates(@PathVariable String base) {
        Optional<CurrencyExchangeRates> currencyExchangeRates = Optional.ofNullable(currencyExchangeService.getRatesOfBaseCurrency(base));
        return currencyExchangeRates.map(ResponseEntity::ok)
                .orElseThrow();
    }
}