package com.pages;

import static org.openqa.selenium.By.xpath;
import static org.openqa.selenium.By.id;
import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.interfaces.IElement;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;
import java.util.List;

public class SearchResultPage extends Form {
    private static final By PROPERTY_CARD_XPATH = xpath("//*[@data-testid = 'property-card']");
    private static final By SEARCH_RESULT_PAGE_ID = id("left");
    private static final By ADDRESS_XPATH = xpath("//*[@data-testid = 'address']");
    private static final By START_DATE_XPATH = xpath("//*[@data-testid = 'date-display-field-start']");
    private static final By END_DATE_XPATH = xpath("//*[@data-testid = 'date-display-field-end']");

    public SearchResultPage() {
        super(SEARCH_RESULT_PAGE_ID, "Search result page");
    }

    public List<IElement> getCards() {
        return getElementFactory().findElements(PROPERTY_CARD_XPATH, ElementType.LABEL);
    }

    public String getCity(IElement element) {
        String[] address = element.findChildElement(ADDRESS_XPATH, ElementType.LABEL).getText().split(",");
        return address[address.length - 1].strip();
    }

    public String[] getDateStart() {
        return getElementFactory().getLabel(START_DATE_XPATH, "Check-in date").getText().split(" ");
    }

    public String[] getDateEnd() {
        return getElementFactory().getLabel(END_DATE_XPATH, "Check-out date").getText().split(" ");
    }
}
