package service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateConverter {

    public static String convertDate(String inputDate) {
        try {

            DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");


            LocalDate date = LocalDate.parse(inputDate, inputFormatter);


            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");


            String outputDate = date.format(outputFormatter);

            return outputDate;
        } catch (Exception e) {

            return "";
        }
    }
}