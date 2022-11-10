package config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:capabilitiesBrowserstack.properties"})

public interface CapabilitiesBrowserStackConfig extends Config
{
    @Key("browserstack.user")
    String browserStackUserName();

    @Key("browserstack.key")
    String browserStackKey();

    @Key("browserstack.networkLogs")
    Boolean browserstackNetworkLogs();

    @Key("browserstack.networkProfile")
    String browserstackNetworkProfile();

    @Key("app")
    String appURL();

    @Key("appActivity")
    String appActivity();

    @Key("device")
    String deviceName();

    @Key("os_version")
    String osVersion();

    @Key("project")
    String projectName();

    @Key("build")
    String buildName();

    @Key("name")
    String testName();

    @Key("noReset")
    Boolean noReset();
}
