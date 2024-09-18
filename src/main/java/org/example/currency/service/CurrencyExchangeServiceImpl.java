package org.example.currency.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.example.currency.domain.CurrencyExchangeRates;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
@Data
public class CurrencyExchangeServiceImpl implements CurrencyExchangeService {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final String key;
    private final String constantPartOfUrl;

    public CurrencyExchangeServiceImpl(
            RestTemplate restTemplate,
            ObjectMapper objectMapper,
            @Value("${currency.endpoint.key}") String key,
            @Value("${currency.endpoint.url}") String constantPartOfUrl
    ) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
        this.key = key;
        this.constantPartOfUrl = constantPartOfUrl;
    }

    @Override
    public CurrencyExchangeRates getRates() {
        String url = constantPartOfUrl + "?access_key=" + key;
        return restTemplate.getForObject(url, CurrencyExchangeRates.class);
    }

    @Override
    public CurrencyExchangeRates getCurrencyExchangeRates(String base, String symbol) {
        String url = constantPartOfUrl + "?access_key=" + key + "&base=" + base + "&symbols=" + symbol;
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        return readAndConvert(base, symbol, response);
    }


    private CurrencyExchangeRates readAndConvert(String base, String symbol, ResponseEntity<String> response) {
        JsonNode root;
        try {
            root = objectMapper.readTree(response.getBody());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        Map<String, Double> rateMap = new HashMap<>();
        rateMap.put(symbol, root.get("rates").doubleValue());
        return CurrencyExchangeRates.builder()
                .base(base)
                .rates(rateMap)
                .build();
    }
}
