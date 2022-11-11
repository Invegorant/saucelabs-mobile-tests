package pages;

import io.qameta.allure.Step;
import pages.components.AlertPanelComponent;
import tools.providers.AlertPanelData;

public class FingerPrintPage
{
    /**** BIOMETRICS ALERT PANEL ****/
    private final AlertPanelData apData = new AlertPanelData();
    private final AlertPanelComponent biometricsPanel = new AlertPanelComponent(apData.getBiometricsPanelTitle(),
            apData.getBiometricsPanelMessage(),
            apData.getBiometricsPanelAcceptButton(),
            apData.getBiometricsPanelCancelButton());

    @Step("Close Biometrics Alert Panel")
    public void closeBiometricsAlertPanel()
    {
        biometricsPanel.pressAcceptAndCloseAlertPanel();
    }
}
