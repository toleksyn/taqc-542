package achornytc.task_03;

import org.junit.jupiter.api.*;

public class GreenCityMenuPageObjectsTestNew {
    private static GreenCityPage greenCityPage;

    @BeforeAll
    static void beforeAll() {
        greenCityPage = new GreenCityPage();
    }

    @BeforeEach
    void beforeEach() {
        greenCityPage
                .openWelcomePage();
    }

    @AfterAll
    static void afterAll() {
        greenCityPage.close();
    }

    @Test
    void verifyEcoNewsHeaderMenuTargetPageTitleCompliance_targetPageTitleShouldBeEqualToExpected() {
        greenCityPage
                .openWelcomePage()
                .openEcoNewsHeaderMenuItem()
    }
}
