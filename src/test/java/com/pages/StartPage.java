package com.pages;

import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.By.id;
import static org.openqa.selenium.By.className;
import aquality.selenium.core.logging.Logger;
import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.interfaces.IElement;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

public class StartPage extends Form {
    private static final By START_PAGE_CSS = cssSelector("[data-testid=herobanner-title1]");
    private static final By DESTINATION_ID = id("ss");
    private static final By PERIOD_CLASS = className("xp__dates");
    private static final By SEARCH_CLASS = className("xp__button");
    private static final By NEXT_MONTH_CSS = cssSelector("[data-bui-ref=calendar-next]");
    private static final By PREV_MONTH_CSS = cssSelector("[data-bui-ref=calendar-prev]");
    private static final By DISPLAYED_MONTH_CLASS = className("bui-calendar__month");
    private static final Logger LOGGER = Logger.getInstance();

    public StartPage() {
        super(START_PAGE_CSS, "Start page");
    }

    public void enterDestination(String destination) {
        getElementFactory().getTextBox(DESTINATION_ID, "Destination input search text field").clearAndType(destination);
    }

    public void enterPeriod(LocalDate start, LocalDate end) {
        getElementFactory().getLabel(PERIOD_CLASS, "Period input search field").click();
        LOGGER.info(String.format("Select check-in date: %1$s", start));
        enterDate(start);
        selectDay(start);
        LOGGER.info(String.format("Select check-out date: %1$s", end));
        enterDate(end);
        selectDay(end);
    }

    public void search() {
        LOGGER.info("Click search button");
        getElementFactory().getButton(SEARCH_CLASS, "Search button").click();
    }

    private void enterDate(LocalDate date) {
        if (getDisplayedYear() == date.getYear()) {
            selectMonth(date);
        } else if (getDisplayedYear() < date.getYear()) {
            do {
                getElementFactory().getLabel(NEXT_MONTH_CSS, "Next month").click();
            } while (getDisplayedYear() != date.getYear());
            selectMonth(date);
        } else {
            do {
                getElementFactory().getLabel(PREV_MONTH_CSS, "Previous month").click();
            } while (getDisplayedYear() != date.getYear());
            selectMonth(date);
        }
    }

    private void selectMonth(LocalDate date) {
        if (getDisplayedMonth().getValue() < date.getMonthValue()) {
            do {
                getElementFactory().getLabel(NEXT_MONTH_CSS, "Next month").click();
            } while (getDisplayedMonth().getValue() != date.getMonthValue());
        } else if (getDisplayedMonth().getValue() > date.getMonthValue()){
            do {
                getElementFactory().getLabel(PREV_MONTH_CSS, "Previous month").click();
            } while (getDisplayedMonth().getValue() != date.getMonthValue());
        }
    }

    private String[] getDisplayedMonthAndYear() {
        List<IElement> elements = getElementFactory().findElements(DISPLAYED_MONTH_CLASS, ElementType.LABEL);
        return elements.get(0).getText().split(" ");
    }

    private Month getDisplayedMonth() {
        String month = getDisplayedMonthAndYear()[0].toUpperCase();
        return Month.valueOf(month);
    }

    private int getDisplayedYear() {
        return Integer.parseInt(getDisplayedMonthAndYear()[1]);
    }

    private void selectDay(LocalDate date) {
        By dateCss = cssSelector(String.format("[data-date='%1$s']", date));
        getElementFactory().getLabel(dateCss, "Date in or out").click();
    }
}
