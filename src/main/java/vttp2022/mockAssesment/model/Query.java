package vttp2022.mockAssesment.model;

import org.springframework.stereotype.Component;

@Component("query")
public class Query {
    private String fsym;
    private String tsyms;
    private Double quantity;
    
    public Double getQuantity() {
        return quantity;
    }
    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }
    public String getFsym() {
        return fsym;
    }
    public void setFsym(String fsym) {
        this.fsym = fsym;
    }
    public String getTsyms() {
        return tsyms;
    }
    public void setTsyms(String tsyms) {
        this.tsyms = tsyms;
    }
}
