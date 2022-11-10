package config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:${env}.properties"})
public interface MobileConfig extends Config
{
    @Key("workingHost")
    String workingHost();

    @Key("login")
    String login();

    @Key("password")
    String password();

    @Key("localeTag")
    String localeTag();

    @Key("url")
    String appiumHost();

    @Key("platformVersion")
    String platformVersion();

    @Key("deviceName")
    String deviceName();

    @Key("deviceOrientation")
    String deviceOrientation();

    @Key("capabilities")
    String capabilities();
}
