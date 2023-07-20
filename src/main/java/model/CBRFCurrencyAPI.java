package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class CBRFCurrencyAPI implements CurrencyAPI {

    @Override
    public CurrencyModel getCurrencyRate(String currencyCode, String date) throws IOException, DocumentException {
        String url = "https://www.cbr.ru/scripts/XML_daily.asp?date_req=" + date;
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod("GET");

        if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
            System.out.println("Failed to fetch data. Status code: " + connection.getResponseCode());
            return null;
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder responseBuilder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            responseBuilder.append(line);
        }
        reader.close();

        Document document = DocumentHelper.parseText(responseBuilder.toString());
        Element rootElement = document.getRootElement();
        for (Object valuteObj : rootElement.elements("Valute")) {
            Element valuteElement = (Element) valuteObj;
            String code = valuteElement.elementText("CharCode");
            if (currencyCode.equals(code)) {
                String rateStr = valuteElement.elementText("Value").replace(",", ".");
                double rate = Double.parseDouble(rateStr);
                String name = getCurrencyName(currencyCode);
                return new CurrencyModel(currencyCode, name, rate);
            }
        }

        return null;
    }

    @Override
    public String getCurrencyName(String currencyCode) throws IOException, DocumentException {
        String url = "https://www.cbr.ru/scripts/XML_valFull.asp";
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod("GET");

        if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
            System.out.println("Failed to fetch data. Status code: " + connection.getResponseCode());
            return "";
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder responseBuilder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            responseBuilder.append(line);
        }
        reader.close();

        Document document = DocumentHelper.parseText(responseBuilder.toString());
        Element rootElement = document.getRootElement();
        for (Object valuteObj : rootElement.elements("Item")) {
            Element valuteElement = (Element) valuteObj;
            String code = valuteElement.elementText("ISO_Char_Code");
            if (currencyCode.equals(code)) {
                return valuteElement.elementText("Name");
            }
        }

        return null;
    }
}