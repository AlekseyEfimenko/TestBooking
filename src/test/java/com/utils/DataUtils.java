package com.utils;

import com.pages.SearchResultPage;
import org.apache.log4j.Logger;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataUtils {
    private static DataUtils instance;
    private final Config config = Config.getInstance();
    private static final Logger LOGGER = Logger.getLogger(DataUtils.class);

    private DataUtils() {}

    public static DataUtils getInstance() {
        if (instance == null) {
            instance = new DataUtils();
        }
        return instance;
    }

    /**
     * Get start URL for test
     * @return URL in String format
     */
    public String getStartURL() {
        return config.getProperties("startUrl");
    }

    /**
     * Get date to check in
     * @return Date in year-month-day format
     */
    public LocalDate getDateIn() {
        int startYear = Integer.parseInt(config.getProperties("yearIn"));
        Month startMonth = Month.valueOf(config.getProperties("monthIn").toUpperCase());
        int startDay = Integer.parseInt(config.getProperties("dayIn"));
        LocalDate now = LocalDate.now();
        if (startYear > (now.getYear() + 1)) {
            LOGGER.error("You have entered incorrect year to check-in");
            throw new NullPointerException("You have entered incorrect year to check-in");
        }
        if (LocalDate.of(startYear, startMonth, startDay).isBefore(now)) {
            LOGGER.error("You have entered incorrect date to check-in");
            throw new NullPointerException("You have entered incorrect date to check-in");
        }
        return LocalDate.of(startYear, startMonth, startDay);
    }

    /**
     * Get date to check out
     * @return Date in year-month-day format
     */
    public LocalDate getDateOut() {
        int endYear = Integer.parseInt(config.getProperties("yearOut"));
        Month endMonth = Month.valueOf(config.getProperties("monthOut").toUpperCase());
        int endDay = Integer.parseInt(config.getProperties("dayOut"));
        LocalDate checkIn = getDateIn();
        LocalDate checkOut = LocalDate.of(endYear, endMonth, endDay);
        if (endYear > (LocalDate.now().getYear() + 1)) {
            LOGGER.error("You have entered incorrect year to check-out");
            throw new NullPointerException("You have entered incorrect year to check-out");
        }
        if (!checkOut.isAfter(checkIn)) {
            LOGGER.error("You have entered incorrect date to check-out");
            throw new NullPointerException("You have entered incorrect date to check-out");
        }
        return checkOut;
    }

    /**
     * Check if the address of all the cards from the page contains the particular city
     * @param resultPage Page, where cards are displayed
     * @param city The city to find in the cards
     * @return true - if all the cards contain the given city in their address; false - if not
     */
    public boolean confirmCity(SearchResultPage resultPage, String city) {
        return resultPage.getCards().stream().allMatch(element -> resultPage.getCity(element).equals(city));
    }

    /**
     * Get check-in date from the page
     * @param resultPage Page to get check-in
     * @return Date in year-month-day format
     */
    public LocalDate getCheckInDate(SearchResultPage resultPage) {
        String[] dates = resultPage.getDateStart();
        int year = Integer.parseInt(dates[3]);
        Month month = Month.valueOf(dates[2].toUpperCase());
        int day = Integer.parseInt(dates[1]);
        return LocalDate.of(year,month, day);
    }

    /**
     * Get check-out date from the page
     * @param resultPage Page to get check-out
     * @return Date in year-month-day format
     */
    public LocalDate getCheckOutDate(SearchResultPage resultPage) {
        String[] dates = resultPage.getDateEnd();
        int year = Integer.parseInt(dates[3]);
        Month month = Month.valueOf(dates[2].toUpperCase());
        int day = Integer.parseInt(dates[1]);
        return LocalDate.of(year,month, day);
    }

    /**
     * Check if list is sorted
     * @param listOfStrings The list to check in
     * @param <T> The type of objects in the list
     * @return true - if list is sorted. Otherwise false
     */
    public static <T extends Comparable<T>> boolean isSorted(List<T> listOfStrings) {
        if (listOfStrings.isEmpty() || listOfStrings.size() == 1) {
            return true;
        }

        Iterator<T> iter = listOfStrings.iterator();
        T current;
        T previous = iter.next();

        while (iter.hasNext()) {
            current = iter.next();
            if (previous.compareTo(current) > 0) {
                return false;
            }
            previous = current;
        }
        return true;
    }

    /**
     * Get list of prices
     * @param resultPage Page to get prices
     * @return List of prices with Double types
     */
    public List<Double> getListOfPrices (SearchResultPage resultPage) {
        List<Double> prices = new ArrayList<>();
        resultPage.getCards().forEach(element ->
                prices.add(Double.parseDouble(resultPage.getPrice(element)
                        .substring(4).replace(",", ""))));
        return prices;
    }
}
