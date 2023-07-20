package model;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

public class CurrencyModel {
    private String currencyCode;
    private String name;
    private double rate;

    public CurrencyModel(String currencyCode, String name, double rate)  {
        this.currencyCode = currencyCode;
        this.name = name;
        this.rate = rate;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public String getName() {
        return name;
    }

    public double getRate() {
        return rate;
    }
}
