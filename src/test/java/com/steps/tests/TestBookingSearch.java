package com.steps.tests;

import static com.utils.DataUtils.getInstance;
import com.utils.Config;
import org.testng.annotations.Test;
import java.time.LocalDate;

public class TestBookingSearch extends BaseTest {
    private static final String START_PAGE = getInstance().getStartURL();
    private static final String DESTINATION = Config.getInstance().getProperties("destination");
    private static final LocalDate DATE_IN = getInstance().getDateIn();
    private static final LocalDate DATE_OUT = getInstance().getDateOut();

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
