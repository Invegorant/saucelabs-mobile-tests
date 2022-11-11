package pages.components;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.appium.java_client.MobileBy;
import io.qameta.allure.Step;

import java.time.Duration;
import java.util.EnumMap;

import static com.codeborne.selenide.Selenide.$;

public class BurgerMenuComponent
{
    /**** LOCATORS ****/
    private final SelenideElement OPEN_MENU_BUTTON = $(MobileBy.xpath("//android.view.ViewGroup[@content-desc='open menu']")),
            BURGER_MENU_CATALOG_BUTTON = $(MobileBy.xpath("//android.view.ViewGroup[@content-desc=\"menu item catalog\"]")),
            BURGER_MENU_WEBVIEW_BUTTON = $(MobileBy.xpath("//android.view.ViewGroup[@content-desc=\"menu item webview\"]")),
            BURGER_MENU_QR_CODE_BUTTON = $(MobileBy.xpath("//android.view.ViewGroup[@content-desc=\"menu item qr code scanner\"]")),
            BURGER_MENU_GEO_LOCATION_BUTTON = $(MobileBy.xpath("//android.view.ViewGroup[@content-desc=\"menu item geo location\"]")),
            BURGER_MENU_DRAWING_BUTTON = $(MobileBy.xpath("//android.view.ViewGroup[@content-desc=\"menu item drawing\"]")),
            BURGER_MENU_ABOUT_BUTTON = $(MobileBy.xpath("//android.view.ViewGroup[@content-desc=\"menu item about\"]")),
            BURGER_MENU_RESET_APP_BUTTON = $(MobileBy.xpath("//android.view.ViewGroup[@content-desc=\"menu item reset app\"]")),
            BURGER_MENU_FINGERPRINT_BUTTON = $(MobileBy.xpath("//android.view.ViewGroup[@content-desc=\"menu item biometrics\"]")),
            BURGER_MENU_LOGIN_BUTTON = $(MobileBy.xpath("//android.view.ViewGroup[@content-desc=\"menu item log in\"]")),
            BURGER_MENU_LOGOUT_BUTTON = $(MobileBy.xpath("//android.view.ViewGroup[@content-desc=\"menu item log out\"]")),
            BURGER_MENU_API_CALLS_BUTTON = $(MobileBy.xpath("//android.view.ViewGroup[@content-desc=\"menu item api calls\"]")),
            BURGER_MENU_SAUCE_BOT_VIDEO_BUTTON = $(MobileBy.xpath("//android.view.ViewGroup[@content-desc=\"menu item sauce bot video\"]"));

    public enum BurgerMenuModulesEnum
    {
        Catalog,
        Webview,
        QrCode,
        GeoLocation,
        Drawing,
        About,
        ResetApp,
        FingerPrint,
        Login,
        Logout,
        ApiCalls,
        SauceBot
    }
    private EnumMap<BurgerMenuModulesEnum, SelenideElement> burgerMenuElements;

    public BurgerMenuComponent()
    {
        initBurgerMenuEnumMap();
    }

    @Step("Select and open '{menuModulesEnum}' module")
    public void selectAndOpenModule(BurgerMenuModulesEnum menuModulesEnum)
    {
        openBurgerMenu();
        burgerMenuElements.get(menuModulesEnum).click();
        burgerMenuElements.get(BurgerMenuModulesEnum.About).shouldBe(Condition.disappear, Duration.ofSeconds(10));
    }

    @Step("Open Burger Menu")
    private void openBurgerMenu()
    {
        if (!burgerMenuElements.get(BurgerMenuModulesEnum.About).isDisplayed())
        {
            OPEN_MENU_BUTTON.shouldBe(Condition.visible, Duration.ofSeconds(10)).click();
            burgerMenuElements.get(BurgerMenuModulesEnum.About).shouldBe(Condition.visible, Duration.ofSeconds(10));
        }
    }



    private void initBurgerMenuEnumMap()
    {
        //ToDo need to improve it with iterator/for but with strict order to prevent wrong enumMap values
        burgerMenuElements = new EnumMap<>(BurgerMenuModulesEnum.class);
        burgerMenuElements.put(BurgerMenuModulesEnum.Catalog, BURGER_MENU_CATALOG_BUTTON);
        burgerMenuElements.put(BurgerMenuModulesEnum.Webview, BURGER_MENU_WEBVIEW_BUTTON);
        burgerMenuElements.put(BurgerMenuModulesEnum.QrCode, BURGER_MENU_QR_CODE_BUTTON);
        burgerMenuElements.put(BurgerMenuModulesEnum.GeoLocation, BURGER_MENU_GEO_LOCATION_BUTTON);
        burgerMenuElements.put(BurgerMenuModulesEnum.Drawing, BURGER_MENU_DRAWING_BUTTON);
        burgerMenuElements.put(BurgerMenuModulesEnum.About, BURGER_MENU_ABOUT_BUTTON);
        burgerMenuElements.put(BurgerMenuModulesEnum.ResetApp, BURGER_MENU_RESET_APP_BUTTON);
        burgerMenuElements.put(BurgerMenuModulesEnum.FingerPrint, BURGER_MENU_FINGERPRINT_BUTTON);
        burgerMenuElements.put(BurgerMenuModulesEnum.Login, BURGER_MENU_LOGIN_BUTTON);
        burgerMenuElements.put(BurgerMenuModulesEnum.Logout, BURGER_MENU_LOGOUT_BUTTON);
        burgerMenuElements.put(BurgerMenuModulesEnum.ApiCalls, BURGER_MENU_API_CALLS_BUTTON);
        burgerMenuElements.put(BurgerMenuModulesEnum.SauceBot, BURGER_MENU_SAUCE_BOT_VIDEO_BUTTON);
    }
}
