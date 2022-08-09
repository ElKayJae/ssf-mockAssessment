package vttp2022.mockAssesment.model;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;


public class Crypto {
    private static final Logger logger = LoggerFactory.getLogger(Crypto.class);

    private Map<String, Double> priceList = new HashMap<>();
    
    private String name;
    
    private Double quantity;
    
    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Double> getPriceList() {
        return priceList;
    }

    public void setPriceList(Map<String, Double> priceList) {
        this.priceList = priceList;
    }

    public static Crypto createCrypto(String json)throws IOException{
        Crypto crypto = new Crypto();
        Map<String,Double> mapping = new ObjectMapper().readValue(json, HashMap.class);
        crypto.setPriceList(mapping);
        logger.info(crypto.getPriceList().toString());
        
        return crypto;

        // try (InputStream is = new ByteArrayInputStream(json.getBytes())) {
        //     JsonReader r = Json.createReader(is);
        //     JsonObject o = r.readObject();
        //     o.;
        //     JsonArray jsonArr = o.asJsonArray();
        //     for (JsonValue jsonValue : jsonArr) {
        //         JsonObject jsonObject = (JsonObject) jsonValue;
                
                
        //     }            
        // }
    }
}
