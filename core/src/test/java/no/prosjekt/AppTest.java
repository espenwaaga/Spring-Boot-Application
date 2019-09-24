package no.prosjekt;

import no.prosjekt.controller.HomeController;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class AppTest {

    @Test
    public void testApp() {
        HomeController hc = new HomeController();
        String result = hc.home();
        assertEquals( result, "Das Boot, reporting for duty!");
    }
}
