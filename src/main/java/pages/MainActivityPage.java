package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.appium.java_client.MobileBy;
import io.qameta.allure.Step;
import pages.components.BurgerMenuComponent;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;

public class MainActivityPage
{
    /**** PAGES ****/
    public final BurgerMenuComponent burgerMenu = new BurgerMenuComponent();

    /**** LOCATORS ****/
    private final SelenideElement HEADER_MODULE_ELEMENT = $(MobileBy.xpath("//android.view.ViewGroup[@content-desc=\"container header\"]/android.widget.TextView"));

    @Step("Check Selected Module has header '{menuModulesEnum}'")
    public void checkModuleHeader(String menuModulesEnum)
    {
        HEADER_MODULE_ELEMENT.shouldHave(Condition.text(menuModulesEnum), Duration.ofSeconds(10));
    }
}
