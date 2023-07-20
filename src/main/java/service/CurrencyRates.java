package service;

import java.io.IOException;
import java.text.DecimalFormat;

import model.CBRFCurrencyAPI;
import model.CurrencyAPI;
import model.CurrencyModel;
import org.dom4j.DocumentException;

import static service.DateConverter.convertDate;

public class CurrencyRates {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: currency_rates --code=USD --date=2022-10-08");
            return;
        }

        String currencyCode = null;
        String date = null;

        for (String arg : args) {
            if (arg.startsWith("--code=")) {
                currencyCode = arg.substring(7);
            } else if (arg.startsWith("--date=")) {
                String inpDate = arg.substring(7);
                date = convertDate(inpDate);
            }
        }

        if (currencyCode == null || date == null) {
            System.out.println("Invalid arguments. Usage: currency_rates --code=USD --date=2022-10-08");
            return;
        }

        try {
            CurrencyAPI currencyAPI = new CBRFCurrencyAPI();
            CurrencyModel currency = currencyAPI.getCurrencyRate(currencyCode, date);

            if (currency != null) {
                DecimalFormat df = new DecimalFormat("#.####");
                System.out.println(currency.getCurrencyCode() + " (" + currency.getName() + "): " + df.format(currency.getRate()));
            } else {
                System.out.println("Unable to fetch currency rate.");
            }
        } catch (IOException | DocumentException e) {
            e.printStackTrace();
        }
    }
}