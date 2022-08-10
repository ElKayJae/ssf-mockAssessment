package vttp2022.mockAssesment.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import vttp2022.mockAssesment.model.Crypto;
import vttp2022.mockAssesment.model.Query;

@Service
public class CryptoCompareService {
    private static final Logger logger = LoggerFactory.getLogger(CryptoCompareService.class);

    private static final String SINGLE_SYMBOL_PRICE = "https://min-api.cryptocompare.com/data/price";

    @Value("${crypto.compare.apikey}")
    String apiKey;
    
    public Optional<Crypto> getCurrentPrice(Query query){

        
        String cryptoPriceUrl = UriComponentsBuilder.fromUriString(SINGLE_SYMBOL_PRICE)
                                .queryParam("fsym", query.getFsym() )
                                .queryParam("tsyms", query.getTsyms())
                                .toUriString();
        
        logger.info(cryptoPriceUrl);

        RestTemplate template = new RestTemplate();
        ResponseEntity<String> resp = null;

        try{
            HttpHeaders headers = new HttpHeaders();
            headers.set("ApiKey", apiKey);
            HttpEntity request = new HttpEntity<>(headers);
            resp = template.exchange(cryptoPriceUrl, HttpMethod.GET, request, String.class);
            Crypto crypto = Crypto.createCrypto(resp.getBody());
            crypto.setName(query.getFsym());
            crypto.setQuantity(query.getQuantity());
            return Optional.of(crypto);
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        } return Optional.empty();
    }
}
