package com.pages;

import static org.openqa.selenium.By.xpath;
import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.By.id;
import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.interfaces.IElement;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;
import java.util.List;

public class SearchResultPage extends Form {
    private static final By PROPERTY_CARD_CSS = xpath("//div[@data-testid='property-card']");
    private static final By SEARCH_RESULT_PAGE_ID = id("left");
    private static final By ADDRESS_CSS = cssSelector("[data-testid=address]");
    private static final By START_DATE_CSS = cssSelector("[data-testid=date-display-field-start]");
    private static final By END_DATE_CSS = cssSelector("[data-testid=date-display-field-end]");
    private static final By SORT_MENU_CSS = cssSelector("[data-testid=sorters-dropdown-trigger]");
    private static final By SORT_BY_PRICE_CSS = cssSelector("[data-id=price]");
    private static final By PRICE_XPATH = xpath("//div[@data-testid = 'price-and-discounted-price']/span[last()]");

    public SearchResultPage() {
        super(SEARCH_RESULT_PAGE_ID, "Search result page");
    }

    public List<IElement> getCards() {
        return getElementFactory().findElements(PROPERTY_CARD_CSS, ElementType.LABEL);
    }

    public String getCity(IElement element) {
        String[] address = element.findChildElement(ADDRESS_CSS, ElementType.LABEL).getText().split(",");
        return address[address.length - 1].strip();
    }

    public String[] getDateStart() {
        return getElementFactory().getLabel(START_DATE_CSS, "Check-in date").getText().split(" ");
    }

    public String[] getDateEnd() {
        return getElementFactory().getLabel(END_DATE_CSS, "Check-out date").getText().split(" ");
    }

    public void sortByPrice() {
        getElementFactory().getButton(SORT_MENU_CSS, "Sorting menu button").click();
        getElementFactory().getButton(SORT_BY_PRICE_CSS, "Sort by price (lowest first) button").click();

    }

    public String getPrice(IElement element) {
        return element.findChildElement(PRICE_XPATH, ElementType.LABEL).getText();
    }
}
