package pages.components;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.appium.java_client.MobileBy;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;

import java.time.Duration;
import java.util.Objects;

import static com.codeborne.selenide.Selenide.$;

public class AlertPanelComponent
{
    /**** ALERT PANEL COMPONENT ****/
    private final SelenideElement ALERT_PANEL_TITLE = $(MobileBy.id("android:id/alertTitle")),
            ALERT_PANEL_MESSAGE = $(MobileBy.id("android:id/message")),
            ALERT_PANEL_ACCEPT_BUTTON = $(MobileBy.id("android:id/button1")),
            ALERT_PANEL_CANCEL_BUTTON = $(MobileBy.id("android:id/button2"));
    private final String alertTitle;
    private final String alertMessage;
    private final String okButton;
    private final String noButton;

    public AlertPanelComponent(String alertTitle, String alertMessage, String okButton, String noButton) {
        this.alertTitle = alertTitle;
        this.alertMessage = alertMessage;
        this.okButton = okButton;
        this.noButton = noButton;
    }

    @Step("Check Alert Panel Texts")
    public void checkAlertPanelText()
    {
        Assertions.assertEquals(ALERT_PANEL_TITLE.getText(), alertTitle);
        Assertions.assertEquals(ALERT_PANEL_MESSAGE.getText(), alertMessage);
        Assertions.assertEquals(ALERT_PANEL_ACCEPT_BUTTON.getText(), okButton);
        if (!Objects.equals(noButton, ""))
        {
            Assertions.assertEquals(ALERT_PANEL_CANCEL_BUTTON.getText(), noButton);
        }
    }

    @Step("Accept Alert and close panel")
    public void pressAcceptAndCloseAlertPanel()
    {
        ALERT_PANEL_ACCEPT_BUTTON.click();
        ALERT_PANEL_TITLE.shouldBe(Condition.disappear, Duration.ofSeconds(10));
    }

    @Step("Cancel Alert and close panel")
    public void pressCancelAndCloseAlertPanel()
    {
        ALERT_PANEL_CANCEL_BUTTON.click();
        ALERT_PANEL_TITLE.shouldBe(Condition.disappear, Duration.ofSeconds(10));
    }
}
