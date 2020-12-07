package achornytc.task_03;

import org.junit.jupiter.api.*;

public class GreenCityMenuPageObjectsTest {
    private static GreenCityPage greenCityPage;
    private final static String WELCOME_PAGE_URL = "https://ita-social-projects.github.io/GreenCityClient/";

    @BeforeAll
    static void beforeAll() {
        greenCityPage = new GreenCityPage();
    }

    @BeforeEach
    void beforeEach() {
        greenCityPage
                .open(WELCOME_PAGE_URL);
    }

    @AfterAll
    static void afterAll() {
        greenCityPage.close();
    }

    private static void verifyHeaderMenuTargetTitleCompliance_targetTitleEqualsExpected(String menuItem,
                                                                                        String pageTitle) {
        String targetPageTitle = greenCityPage
                .openHeaderMenuItem(menuItem)
                .getPageTitle();

        Assertions.assertEquals(targetPageTitle, pageTitle);
    }

    @Test
    void verifyEcoNewsHeaderMenuItem_forTargetTitleCompliance() {
        verifyHeaderMenuTargetTitleCompliance_targetTitleEqualsExpected("Eco news", "Eco news");
    }

    @Test
    void verifyTipsTricksHeaderMenuItem_forTargetTitleCompliance() {
        verifyHeaderMenuTargetTitleCompliance_targetTitleEqualsExpected("Tips & Tricks", "Tips & Tricks");
    }

    @Test
    void verifyPlacesHeaderMenuItem_forTargetTitleCompliance() {
        verifyHeaderMenuTargetTitleCompliance_targetTitleEqualsExpected("Places", "Places");
    }

    @Test
    void verifyAboutUsHeaderMenuItem_forTargetTitleCompliance() {
        verifyHeaderMenuTargetTitleCompliance_targetTitleEqualsExpected("About us", "About us");
    }

    private static void verifyFooterMenuTargetTitleCompliance_targetTitleEqualsExpected(String menuItem,
                                                                                        String pageTitle) {
        String targetPageTitle = greenCityPage
                .openFooterMenuItem(menuItem)
                .getPageTitle();

        Assertions.assertEquals(targetPageTitle, pageTitle);
    }

    @Test
    void verifyEcoNewsFooterMenuItem_forTargetTitleCompliance() {
        verifyFooterMenuTargetTitleCompliance_targetTitleEqualsExpected("Eco news", "Eco news");
    }

    @Test
    void verifyTipsTricksFooterMenuItem_forTargetTitleCompliance() {
        verifyFooterMenuTargetTitleCompliance_targetTitleEqualsExpected("Tips & Tricks", "Tips & Tricks");
    }

    @Test
    void verifyPlacesFooterMenuItem_forTargetTitleCompliance() {
        verifyFooterMenuTargetTitleCompliance_targetTitleEqualsExpected("Places", "Places");
    }

    @Test
    void verifyAboutUsFooterMenuItem_forTargetTitleCompliance() {
        verifyFooterMenuTargetTitleCompliance_targetTitleEqualsExpected("About us", "About us");
    }


}
