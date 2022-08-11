package vttp2022.mockAssesment.controller;


import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import vttp2022.mockAssesment.model.Crypto;
import vttp2022.mockAssesment.model.Order;
import vttp2022.mockAssesment.model.Query;
import vttp2022.mockAssesment.service.CryptoCompareService;

@RestController
@RequestMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE )
public class CryptoRestController {
    private static final Logger logger = LoggerFactory.getLogger(CryptoRestController.class);
    
    @Autowired
    CryptoCompareService service;
    
    @Autowired
    Query query;

    @Autowired
    Order order;

    @PostMapping
    public ResponseEntity<String> addCrypto(@RequestParam String fsym, @RequestParam String tsyms, @RequestParam Double qty){
        query.setFsym(fsym);
        query.setTsyms(tsyms);
        query.setQuantity(qty);
        Optional<Crypto> crypto = service.getCurrentPrice(query);
        order.addCrypto(crypto.get());

        return ResponseEntity.ok(order.getCryptoListName().toString());
        
    }
    
    @GetMapping
    public ResponseEntity<String> calculatePrice(@RequestParam String sym){
        logger.info(order.getCryptoListName().toString());
        Double totalPrice = order.calculatePrice(sym);

        JsonObject responseJson = Json.createObjectBuilder()
                    .add("orderId", order.getId())
                    .add("total", totalPrice)
                    .build();

        return ResponseEntity.ok(responseJson.toString());
    }

}
