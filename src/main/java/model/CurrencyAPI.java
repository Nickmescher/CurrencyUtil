package model;

import java.io.IOException;
import org.dom4j.DocumentException;

public interface CurrencyAPI {
    CurrencyModel getCurrencyRate(String currencyCode, String date) throws IOException, DocumentException;

    String getCurrencyName(String currencyCode) throws IOException, DocumentException;
}
