package achornytc.task_03;

import org.junit.jupiter.api.*;

public class GreenCityMenuPageObjectsTest {
    private static GreenCityPage greenCityPage;

    @BeforeAll
    static void beforeAll() {
        greenCityPage = new GreenCityPage();
        greenCityPage.openWelcomePage();
    }

    @AfterAll
    static void afterAll() {
        greenCityPage.close();
    }

    @Test
    void verifyEcoNewsHeaderMenuItem_forTargetTitleCompliance() {
        String ecoNewsActualPageTitle = greenCityPage
                .openEcoNewsHeaderMenuItem()
                .getPageTitle();

        Assertions.assertEquals(greenCityPage.getEcoNewsExpectedPageTitle(), ecoNewsActualPageTitle);
    }

    @Test
    void verifyTipsTricksHeaderMenuItem_forTargetTitleCompliance() {
        String tipsTricksActualPageTitle = greenCityPage
                .openTipsTricksHeaderMenuItem()
                .getPageTitle();

        Assertions.assertEquals(greenCityPage.getTipsTricksExpectedPageTitle(), tipsTricksActualPageTitle);
    }

    @Test
    void verifyPlacesHeaderMenuItem_forTargetTitleCompliance() {
        String placesActualPageTitle = greenCityPage
                .openPlacesHeaderMenuItem()
                .getPageTitle();

        Assertions.assertEquals(greenCityPage.getPlacesExpectedPageTitle(), placesActualPageTitle);
    }

    @Test
    void verifyAboutUsHeaderMenuItem_forTargetTitleCompliance() {
        String aboutUsActualPageTitle = greenCityPage
                .openAboutUsHeaderMenuItem()
                .getPageTitle();

        Assertions.assertEquals(greenCityPage.getAboutUsExpectedPageTitle(), aboutUsActualPageTitle);
    }

    @Test
    void verifyEcoNewsFooterMenuItem_forTargetTitleCompliance() {
        String ecoNewsActualPageTitle = greenCityPage
                .openEcoNewsFooterMenuItem()
                .getPageTitle();

        Assertions.assertEquals(greenCityPage.getEcoNewsExpectedPageTitle(), ecoNewsActualPageTitle);
    }

    @Test
    void verifyTipsTricksFooterMenuItem_forTargetTitleCompliance() {
        String tipsTricksActualPageTitle = greenCityPage
                .openTipsTricksFooterMenuItem()
                .getPageTitle();

        Assertions.assertEquals(greenCityPage.getTipsTricksExpectedPageTitle(), tipsTricksActualPageTitle);
    }

    @Test
    void verifyPlacesFooterMenuItem_forTargetTitleCompliance() {
        String placesActualPageTitle = greenCityPage
                .openPlacesFooterMenuItem()
                .getPageTitle();

        Assertions.assertEquals(greenCityPage.getPlacesExpectedPageTitle(), placesActualPageTitle);
    }

    @Test
    void verifyAboutUsFooterMenuItem_forTargetTitleCompliance() {
        String aboutUsActualPageTitle = greenCityPage
                .openAboutUsFooterMenuItem()
                .getPageTitle();

        Assertions.assertEquals(greenCityPage.getAboutUsExpectedPageTitle(), aboutUsActualPageTitle);
    }


}
