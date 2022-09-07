package com.steps.tests;

import com.utils.Config;
import org.testng.annotations.Test;

public class TestBookingSearch extends BaseTest {
    private static final String DESTINATION = Config.getInstance().getProperties("destination1");

    @Test
    public void testSearch() {
        steps.navigateToSite(START_PAGE);
        steps.selectCity(DESTINATION);
        steps.selectDateInAndOut(DATE_IN, DATE_OUT);
        steps.startSearching();
        steps.assertCityIsCorrect(DESTINATION);
        steps.assertDatesAreCorrect(DATE_IN, DATE_OUT);
    }
}
