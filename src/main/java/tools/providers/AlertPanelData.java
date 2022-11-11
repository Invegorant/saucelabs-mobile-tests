package tools.providers;

import lombok.Data;

@Data
public class AlertPanelData
{
    //ToDo Rework to get translations from property files
    /**** RESET APP STATE PANEL****/
    private final String resetAppPanelTitle = "Reset App State";
    private final String resetAppPanelMessage = "Are you sure you sure you want to reset the app state?";
    private final String resetAppPanelAcceptButton = "RESET APP";
    private final String resetAppPanelCancelButton = "CANCEL";

    /**** BIOMETRICS ALERT PANEL ****/
    private final String biometricsPanelTitle = "Biometrics";
    private final String biometricsPanelMessage = "Biometrics is or not supported or not enabled on your device. Please check your device or your settings.";
    private final String biometricsPanelAcceptButton = "OK";
    private final String biometricsPanelCancelButton = "";
}
