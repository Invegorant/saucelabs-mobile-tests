package config;

import com.codeborne.selenide.WebDriverProvider;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

public class AndroidDriverProvider implements WebDriverProvider
{
    public static AppiumDriver driver;
    @Nonnull
    @Override
    @CheckReturnValue
    public WebDriver createDriver(DesiredCapabilities capabilities)
    {
        for (Map.Entry<String, String> entry : BaseSetup.capabilities_vars.entrySet())
        {
            capabilities.setCapability(entry.getKey(), entry.getValue());
        }
        return driver = new AndroidDriver(getBaseSetupUrl(), capabilities);
    }

    public static URL getBaseSetupUrl()
    {
        try
        {
            return new URL(BaseSetup.appiumHost);
        }
        catch (MalformedURLException e)
        {
            throw new RuntimeException(e);
        }
    }
}
