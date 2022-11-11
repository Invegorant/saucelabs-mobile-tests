package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.appium.java_client.MobileBy;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;

public class FingerPrintPage
{
    /**** BIOMETRICS ALERT PANEL ****/
    private final SelenideElement BIOMETRICS_ALERT_TITLE = $(MobileBy.id("android:id/alertTitle")),
            BIOMETRICS_ALERT_MESSAGE = $(MobileBy.id("android:id/message")),
            BIOMETRICS_ALERT_OK_BUTTON = $(MobileBy.id("android:id/button1"));

    @Step("Close Biometrics Alert Panel")
    public void closeBiometricsAlertPanel()
    {
        BIOMETRICS_ALERT_TITLE.shouldBe(Condition.visible, Duration.ofSeconds(10));
        BIOMETRICS_ALERT_OK_BUTTON.click();
        BIOMETRICS_ALERT_TITLE.shouldBe(Condition.disappear, Duration.ofSeconds(10));
    }
}
