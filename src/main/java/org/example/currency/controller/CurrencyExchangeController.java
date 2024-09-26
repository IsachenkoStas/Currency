package org.example.currency.controller;

import lombok.RequiredArgsConstructor;
import org.example.currency.dto.CurrencyDto;
import org.example.currency.dto.CurrencyRateDto;
import org.example.currency.dto.CurrencyExchangeRates;
import org.example.currency.service.CurrencyExchangeService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class CurrencyExchangeController {

    private final CurrencyExchangeService currencyExchangeService;
    private final ModelMapper modelMapper;

    @GetMapping("/exchange-rates")
    public ResponseEntity<CurrencyRateDto> getExchangeRates(@RequestParam String base, @RequestParam String target) {
        Optional<CurrencyRateDto> currencyExchangeRates = Optional.ofNullable(currencyExchangeService.getCurrencyExchangeRates(base, target));
        return currencyExchangeRates.map(ResponseEntity::ok)
                .orElseThrow();
    }

    @PostMapping("/add-currency")
    public ResponseEntity<Void> addNewCurrency(@RequestBody CurrencyDto currencyDto) {
        currencyExchangeService.addNewCurrency(currencyDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<CurrencyDto>> getAllCurrencies() {
        return new ResponseEntity<>(currencyExchangeService.getAllCurrencies().stream()
                .map(currency -> modelMapper.map(currency, CurrencyDto.class)).toList(), HttpStatus.OK);
    }

    @GetMapping("/rates/{base}")
    public ResponseEntity<CurrencyExchangeRates> getRates(@PathVariable String base) {
        Optional<CurrencyExchangeRates> filteredExchangeRates = Optional.ofNullable(currencyExchangeService.getFilteredExchangeRates(base));
        return filteredExchangeRates.map(ResponseEntity::ok)
                .orElseThrow();
    }
}