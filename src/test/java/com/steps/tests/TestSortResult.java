package com.steps.tests;

import com.utils.Config;
import org.testng.annotations.Test;

public class TestSortResult extends BaseTest {
    private static final String CITY = Config.getInstance().getProperties("destination2");

    @Test
    public void testSort() {
        steps.navigateToSite(START_PAGE);
        steps.selectCity(CITY);
        steps.selectDateInAndOut(DATE_IN, DATE_OUT);
        steps.startSearching();
        steps.sortPropertiesASC();
        steps.assertPriceIsSorted();
    }
}
