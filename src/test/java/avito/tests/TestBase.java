package avito.tests;

import avito.pages.MainPage;
import com.codeborne.selenide.Selenide;
import config.WebDriverProvider;
import helpers.Attach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Configuration.browser;
import static io.qameta.allure.Allure.step;

public class TestBase {

    final MainPage mainPage = new MainPage();

    @BeforeAll
    public static void setUp() {
        new WebDriverProvider();
    }

    @BeforeEach
    void beforeEach() {
        step("Открытие главной страницы", () -> {
            mainPage.openMainPage()
                    .checkLoadingHeaderNavigation();
        });

        step("Выбор локации и принятие информации о сборе куков", () -> {
            mainPage.closeCookieInformation()
                    .pressChangeLocation()
                    .clearFieldLocation()
                    .enterSearchLocation("Все регионы")
                    .checkAndChooseEnteredLocation("Все регионы")
                    .selectLocationFromDropDownList()
                    .checkCurrentLocation("Во всех регионах");
        });
    }

    @AfterEach
    public void afterEach() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        if (browser.equals("chrome")) {
            Attach.browserConsoleLogs();
        }
        Attach.addVideo();
        Selenide.closeWebDriver();
        Selenide.clearBrowserCookies();
    }
}