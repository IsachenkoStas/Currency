package org.example.currency.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.example.currency.DTO.CurrencyRateDto;
import org.example.currency.domain.CurrencyExchangeRates;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
    public CurrencyExchangeRates getRatesOfBaseCurrency(String base) {
        String url = constantPartOfUrl + key + "/latest/" + base;
        return restTemplate.getForObject(url, CurrencyExchangeRates.class);
    }

    @Override
    public CurrencyRateDto getCurrencyExchangeRates(String base, String target) {
        String url = constantPartOfUrl + key + "/pair/" + base + "/" + target;
        return restTemplate.getForObject(url, CurrencyRateDto.class);
    }
}
