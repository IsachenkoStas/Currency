package org.example.currency.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.example.currency.domain.Currency;
import org.example.currency.dto.CurrencyDto;
import org.example.currency.dto.CurrencyRateDto;
import org.example.currency.dto.CurrencyExchangeRates;
import org.example.currency.repo.CurrencyRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Data
@RequiredArgsConstructor
public class CurrencyExchangeServiceImpl implements CurrencyExchangeService {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final CurrencyRepo currencyRepo;
    private final ModelMapper modelMapper;

    @Value("${currency.endpoint.key}")
    private String key;

    @Value("${currency.endpoint.url}")
    private String constantPartOfUrl;

    @Override
    public void addNewCurrency(CurrencyDto currencyDto) {
        Currency currency = new Currency();
        modelMapper.map(currencyDto, currency);
        currencyRepo.save(currency);
    }

    @Override
    public List<Currency> getAllCurrencies() {
        return currencyRepo.findAll();
    }

    @Override
    public CurrencyRateDto getCurrencyExchangeRates(String base, String target) {
        String url = constantPartOfUrl + key + "/pair/" + base + "/" + target;
        return restTemplate.getForObject(url, CurrencyRateDto.class);
    }

    public CurrencyExchangeRates getFilteredExchangeRates(String base) {
        String url = constantPartOfUrl + key + "/latest/" + base;
        CurrencyExchangeRates response = restTemplate.getForObject(url, CurrencyExchangeRates.class);

        List<Currency> currencies = currencyRepo.findAll();
        List<String> currencyCodes = currencies.stream()
                .map(Currency::getCurrencyCode)
                .toList();

        Map<String, Double> rates = Objects.requireNonNull(response).getConversion_rates();
        Map<String, Double> filteredRates = rates.entrySet().stream()
                .filter(entry -> currencyCodes.contains(entry.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        CurrencyExchangeRates currencyExchangeRates = new CurrencyExchangeRates();
        currencyExchangeRates.setConversion_rates(filteredRates);
        currencyExchangeRates.setBase_code(base);
        return currencyExchangeRates;
    }
}