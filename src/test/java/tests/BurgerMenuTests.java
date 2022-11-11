package tests;

import config.BaseSetup;
import io.qameta.allure.Description;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import pages.FingerPrintPage;
import pages.MainActivityPage;
import pages.components.BurgerMenuComponent;

import static io.qameta.allure.Allure.step;

@Tag("BURGER_MENU")
public class BurgerMenuTests extends BaseSetup
{
    /**** PAGES ****/
    private final MainActivityPage mainActivityPage = new MainActivityPage();
    private final FingerPrintPage fingerPrintPage = new FingerPrintPage();

    /**** TEST DATA ****/

    @Test
    @Tags({ @Tag("TESTAPP"), @Tag("CATALOG")})
    @Story("Check Burger Menu")
    @Description("Check 'Catalog' module is opened from 'Burger Menu'")
    void checkCatalogModule() {
        step("Open 'Burger Menu' -> 'Catalog', check it's opened", () -> {
            mainActivityPage.burgerMenu.selectAndOpenModule(BurgerMenuComponent.BurgerMenuModulesEnum.Catalog);
            mainActivityPage.checkModuleHeader("Products");
        });
    }

    @Test
    @Tags({ @Tag("TESTAPP"), @Tag("WEBVIEW")})
    @Story("Check Burger Menu")
    @Description("Check 'Webview' module is opened from 'Burger Menu'")
    void checkWebViewModule() {
        step("Open 'Burger Menu' -> 'Webview', check it's opened", () -> {
            mainActivityPage.burgerMenu.selectAndOpenModule(BurgerMenuComponent.BurgerMenuModulesEnum.Webview);
            mainActivityPage.checkModuleHeader(BurgerMenuComponent.BurgerMenuModulesEnum.Webview.name());
        });
    } @Test
    @Tags({ @Tag("TESTAPP"), @Tag("DRAWING")})
    @Story("Check Burger Menu")
    @Description("Check 'Drawing' module is opened from 'Burger Menu'")
    void checkDrawingModule() {
        step("Open 'Burger Menu' -> 'Drawing', check it's opened", () -> {
            mainActivityPage.burgerMenu.selectAndOpenModule(BurgerMenuComponent.BurgerMenuModulesEnum.Drawing);
            mainActivityPage.checkModuleHeader(BurgerMenuComponent.BurgerMenuModulesEnum.Drawing.name());
        });
    }

    @Test
    @Tags({ @Tag("TESTAPP"), @Tag("ABOUT")})
    @Story("Check Burger Menu")
    @Description("Check 'About' module is opened from 'Burger Menu'")
    void checkAboutModule() {
        step("Open 'Burger Menu' -> 'About', check it's opened", () -> {
            mainActivityPage.burgerMenu.selectAndOpenModule(BurgerMenuComponent.BurgerMenuModulesEnum.About);
            mainActivityPage.checkModuleHeader(BurgerMenuComponent.BurgerMenuModulesEnum.About.name());
        });
    }
    @Test
    @Tags({ @Tag("TESTAPP"), @Tag("FINGERPRINT")})
    @Story("Check Burger Menu")
    @Description("Check 'FingerPrint' module is opened from 'Burger Menu'")
    void checkFingerPrintModule() {
        step("Open 'Burger Menu' -> 'FingerPrint', check it's opened", () -> {
            mainActivityPage.burgerMenu.selectAndOpenModule(BurgerMenuComponent.BurgerMenuModulesEnum.FingerPrint);
            fingerPrintPage.closeBiometricsAlertPanel();
            mainActivityPage.checkModuleHeader(BurgerMenuComponent.BurgerMenuModulesEnum.FingerPrint.name());
        });
    }

    @Test
    @Tags({ @Tag("TESTAPP"), @Tag("LOGIN")})
    @Story("Check Burger Menu")
    @Description("Check 'Login' module is opened from 'Burger Menu'")
    void checkLoginModule() {
        step("Open 'Burger Menu' -> 'Login', check it's opened", () -> {
            mainActivityPage.burgerMenu.selectAndOpenModule(BurgerMenuComponent.BurgerMenuModulesEnum.Login);
            mainActivityPage.checkModuleHeader(BurgerMenuComponent.BurgerMenuModulesEnum.Login.name());
        });
    }

    @Test
    @Tags({ @Tag("TESTAPP"), @Tag("APICALLS")})
    @Story("Check Burger Menu")
    @Description("Check 'ApiCalls' module is opened from 'Burger Menu'")
    void checkApiCallsModule() {
        step("Open 'Burger Menu' -> 'ApiCalls', check it's opened", () -> {
            mainActivityPage.burgerMenu.selectAndOpenModule(BurgerMenuComponent.BurgerMenuModulesEnum.ApiCalls);
            mainActivityPage.checkModuleHeader("Api Calls");
        });
    }

    @Test
    @Tags({ @Tag("TESTAPP"), @Tag("SAUCEBOT")})
    @Story("Check Burger Menu")
    @Description("Check 'SauceBot' module is opened from 'Burger Menu'")
    void checkSauceBotModule() {
        step("Open 'Burger Menu' -> 'SauceBot', check it's opened", () -> {
            mainActivityPage.burgerMenu.selectAndOpenModule(BurgerMenuComponent.BurgerMenuModulesEnum.SauceBot);
            mainActivityPage.checkModuleHeader(BurgerMenuComponent.BurgerMenuModulesEnum.SauceBot.name());
        });
    }
}
