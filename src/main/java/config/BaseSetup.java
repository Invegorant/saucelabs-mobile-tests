package config;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.google.common.collect.ImmutableMap;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.*;
import tools.Attachments;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Configuration.browserSize;
import static com.codeborne.selenide.Selenide.open;
import static com.github.automatedowl.tools.AllureEnvironmentWriter.allureEnvironmentWriter;
import static tools.Attachments.getSessionId;

public class BaseSetup extends AndroidDriverProvider
{
    /**** SETTINGS ****/
    // Resources

    private static final String RESOURCES_POSTFIX = ".properties";
    private static final String RESOURCES_PATH = "src/test/resources";
    public static final String ENV_RESOURCES_PREFIX = System.getProperty("env", String.valueOf(Env.envLocal));
    // Maps
    private static final Map<String, String> env_vars = new HashMap<>();
    public static final Map<String, String> capabilities_vars = new HashMap<>();

    /**** MAIN VARS****/
    public static String workingHost = null;
    public static String login = null;
    public static String passWord = null;
    public static String localeTag = null;
    public static String appiumHost = null;
    public static String capabilities = null;
    public static String deviceName = null;
    public static String deviceOrientation = null;
    public static final long WAITING_TIME_OUT = 20000;

    /**** BROWSERSTACK GLOBAL VARS ****/
    public static String appURL = null;
    public static String osVersion = null;
    public static String buildName = null;

    private enum Env
    {
        envBrowserstack,
        envLocal,
        envEmuAndroid,
    }

    @BeforeAll
    public static void setUp()
    {
        try
        {
            initEnvVars();
            initSelenideVars();
            setAllureEnvironment();
        }
        catch (final Throwable t)
        {
            System.out.println("No fun...: " + t.getMessage());
            t.printStackTrace();
        }
    }

    @BeforeEach
    void startDriver(TestInfo testInfo)
    {
        changeExactCapability("name", testInfo.getDisplayName());
        open();
    }

    @AfterEach
    void resetApp()
    {
        Attachments.screenshotAs("Screen after test");
        Attachments.pageSource();
        String sessionId = getSessionId();
        driver.resetApp();
        driver.quit();
        if (capabilities_vars.get("browserstack.user") != null)
        {
            JsonPath jsonPath = Attachments.getBrowserStackResponse(sessionId);
            Attachments.attachVideo(jsonPath);
            Attachments.deviceLogs(jsonPath);
        }
    }

    @AfterAll
    public static void tearDownDriver()
    {
        driver.quit();
    }

    private static void initSelenideVars()
    {
        baseUrl = workingHost;
        browserSize = null;
        Configuration.browser = AndroidDriverProvider.class.getName();
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true));
    }

    private static void initEnvVars() throws IOException
    {
        workingHost = MobileConfigHelper.workingHost();
        login = MobileConfigHelper.login();
        passWord = MobileConfigHelper.password();
        localeTag = MobileConfigHelper.localeTag();
        appiumHost = MobileConfigHelper.appiumHost();
        appURL = MobileConfigHelper.appURL();
        capabilities = MobileConfigHelper.capabilities();
        osVersion = MobileConfigHelper.osVersion();
        deviceName = MobileConfigHelper.deviceName();
        deviceOrientation = MobileConfigHelper.deviceOrientation();
        buildName = MobileConfigHelper.buildName();
        //    parseCapabilitiesFiles(System.getProperty("capabilities", getEnvVar("capabilities", String.valueOf(Capabilities.capabilitiesLocal))));
        parseCapabilitiesFiles(capabilities);
        //    parseCapabilitiesFiles(System.getProperty(capabilities));
        if (capabilities_vars.containsKey("browserstack.user"))
        {
            changeExactCapability("build", buildName);
            changeExactCapability("app", appURL);
            changeExactCapability("os_version", osVersion);
        }
        changeExactCapability("device", deviceName);
        changeExactCapability("deviceOrientation", deviceOrientation);
    }

    private static void changeExactCapability(String key, String value)
    {
        if (capabilities_vars.get(key) != null)
        {
            capabilities_vars.replace(key, value);
        }
        else
        {
            capabilities_vars.put(key, value);
        }
    }

    private static void setAllureEnvironment()
    {
        allureEnvironmentWriter(
                ImmutableMap.<String, String>builder()
                        .putAll(capabilities_vars)
                        .put("Working Host", workingHost)
                        .put("login", login)
                        .put("url", appiumHost)
                        .put("tags", System.getProperty("groups", ""))
                        .put("global environment", System.getenv().toString())
                        .build());
    }

    protected static String getEnvVar(final String name)
    {
        return getEnvVar(name, "");
    }

    private static String getEnvVar(final String name, final String defaultValue)
    {
        return Optional.ofNullable(env_vars.get(name)).orElse(defaultValue);
    }

    private static void parseEnvFiles() throws IOException
    {
        getAllEnvFiles().forEach(path -> {
            final ResourceBundle bundle = ResourceBundle
                    .getBundle(path.getFileName().toString().replace(RESOURCES_POSTFIX, ""));

            bundle.keySet().forEach(key -> env_vars.put(key, bundle.getString(key)));
        });
    }

    private static List<Path> getAllEnvFiles() throws IOException
    {
        return Files.walk(Paths.get(RESOURCES_PATH)).filter(p -> p.toString().contains(ENV_RESOURCES_PREFIX))
                .collect(Collectors.toList());
    }

    private static List<Path> getAllEnvFiles(String propertiesFileName) throws IOException
    {
        return Files.walk(Paths.get(RESOURCES_PATH)).filter(p -> p.toString().contains(propertiesFileName))
                .collect(Collectors.toList());
    }

    private static void parseCapabilitiesFiles(String capabilitiesProperties) throws IOException
    {
        getAllEnvFiles(capabilitiesProperties).forEach(path -> {
            final ResourceBundle bundle = ResourceBundle
                    .getBundle(path.getFileName().toString().replace(RESOURCES_POSTFIX, ""));

            bundle.keySet().forEach(key -> capabilities_vars.put(key, bundle.getString(key)));
        });
    }
}
