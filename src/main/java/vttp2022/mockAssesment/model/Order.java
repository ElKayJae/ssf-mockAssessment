package vttp2022.mockAssesment.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component ("order")
public class Order {
    private static final Logger logger = LoggerFactory.getLogger(Order.class);
    private List<Crypto> cryptoList = new ArrayList<>();
    private String id;

    public List<Crypto> getCryptoList() {
        return cryptoList;
    }

    public List<String> getCryptoListName(){
        List<String> list = new ArrayList<>();
        for (Crypto crypto:cryptoList){
            list.add(crypto.getName());
        }
        return list;
    }

    public String getId() {
        return id;
    }

    public Order(){
        this.id=generateId(8);
    }

    private synchronized String generateId(int numchars) {
        String generatedId = UUID.randomUUID()
                            .toString()
                            .substring(0,numchars);
        return generatedId;
    }
    public void addCrypto(Crypto crypto){
        this.cryptoList.add(crypto);
    }

    public Double calculatePrice(String currencySymbol){
        Double totalPrice= 0.0;
        for (Crypto crypto:cryptoList){
            Double price = crypto.getPriceList().get(currencySymbol);
            Double quantity = crypto.getQuantity();
            totalPrice += price*quantity;
        }
        return totalPrice;
    }

}
