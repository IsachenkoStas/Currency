package org.example.currency.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.example.currency.domain.CurrencyExchangeRates;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class CurrencyExchangeServiceImpl implements CurrencyExchangeService {

    private final RestTemplate restTemplate;
    private static final String API_URL = "https://api.exchangeratesapi.io/v1/latest";
    private static final String API_KEY = "e87e83103dc6ee1211b56e73c606c0cd";
    //private final ObjectMapper jacksonObjectMapper;

    @Override
    public CurrencyExchangeRates getCurrencyExchangeRates() {
        String url = API_URL/* + "?api_key=" + API_KEY + "&currency=" + currency*/;
        return restTemplate.getForObject(API_URL, CurrencyExchangeRates.class);
    }

    /*private CurrencyExchangeRates readAndConvert(String currency, ResponseEntity<String> response) {
        JsonNode root;
        try {
            root = jacksonObjectMapper.readTree(response.getBody());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        //надо понять что возвращает и что вытаскивает из этого дерева(ответа апи)
        //TODO не забывать делать промежуточные коммиты в течении работы над веткой
    }*/
}
