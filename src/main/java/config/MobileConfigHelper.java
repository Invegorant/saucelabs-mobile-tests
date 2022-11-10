package config;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.ConfigFactory;

import static config.BaseSetup.ENV_RESOURCES_PREFIX;

public class MobileConfigHelper
{
    private static MobileConfig getConfig()
    {
        if (System.getProperty("env") == null)
        {
            System.setProperty("env", ENV_RESOURCES_PREFIX);
        }
        return ConfigFactory.newInstance().create(MobileConfig.class, System.getProperties());
    }

    private static CapabilitiesBrowserStackConfig getCapabilitiesBrowserstackConfig()
    {
        return ConfigFactory.newInstance().create(CapabilitiesBrowserStackConfig.class, System.getProperties());
    }

    @Config.Key("capabilities")
    public static String capabilities()
    {
        return getConfig().capabilities();
    }

    @Config.Key("deviceOrientation")
    public static String deviceOrientation()
    {
        return getConfig().deviceOrientation();
    }

    @Config.Key("workingHost")
    public static String workingHost()
    {
        return getConfig().workingHost();
    }

    @Config.Key("login")
    public static String login()
    {
        return getConfig().login();
    }

    @Config.Key("password")
    public static String password()
    {
        return getConfig().password();
    }

    @Config.Key("localeTag")
    public static String localeTag()
    {
        return getConfig().localeTag();
    }

    @Config.Key("url")
    public static String appiumHost()
    {
        return getConfig().appiumHost();
    }

    @Config.Key("browserstack.user")
    public static String browserStackUserName()
    {
        return getCapabilitiesBrowserstackConfig().browserStackUserName();
    }

    @Config.Key("browserstack.key")
    public static String browserStackKey()
    {
        return getCapabilitiesBrowserstackConfig().browserStackKey();
    }

    @Config.Key("browserstack.networkLogs")
    public static Boolean browserstackNetworkLogs()
    {
        return getCapabilitiesBrowserstackConfig().browserstackNetworkLogs();
    }

    @Config.Key("browserstack.networkProfile")
    public static String browserstackNetworkProfile()
    {
        return getCapabilitiesBrowserstackConfig().browserstackNetworkProfile();
    }

    @Config.Key("app")
    public static String appURL()
    {
        return getCapabilitiesBrowserstackConfig().appURL();
    }

    @Config.Key("deviceName")
    public static String deviceName()
    {
        return getConfig().deviceName();
    }

    @Config.Key("os_version")
    public static String osVersion()
    {
        return getCapabilitiesBrowserstackConfig().osVersion();
    }

    @Config.Key("project")
    public static String projectName()
    {
        return getCapabilitiesBrowserstackConfig().projectName();
    }

    @Config.Key("build")
    public static String buildName()
    {
        return getCapabilitiesBrowserstackConfig().buildName();
    }

    @Config.Key("name")
    public static String testName()
    {
        return getCapabilitiesBrowserstackConfig().testName();
    }

    @Config.Key("noReset")
    public static Boolean noReset()
    {
        return getCapabilitiesBrowserstackConfig().noReset();
    }
}
