package tools;

import config.BaseSetup;
import io.qameta.allure.Attachment;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.nio.charset.StandardCharsets;

import static com.codeborne.selenide.Selenide.sleep;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.restassured.RestAssured.given;

public class Attachments extends BaseSetup
{
    private static int counter = 0;
    private static Response response = null;

    @Attachment(value = "Page source", type = "text/plain")
    public static byte[] pageSource()
    {
        return getWebDriver().getPageSource().getBytes(StandardCharsets.UTF_8);
    }

    @Attachment(value = "Device logs", type = "text/plain")
    public static byte[] deviceLogs(JsonPath jsonPath)
    {
        getDeviceLogs(getBrowserstackDeviceLogsURL(jsonPath));
        String deviceLogs = null;
        if (response != null)
        {
            deviceLogs = response.asString();
        }
        return deviceLogs.getBytes(StandardCharsets.UTF_8);
    }

    @Attachment(value = "{attachName}", type = "image/png")
    public static byte[] screenshotAs(String attachName)
    {
        return ((TakesScreenshot) getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }

    @Attachment(value = "Video", type = "text/html", fileExtension = ".html")
    public static String attachVideo(JsonPath jsonPath)
    {
        return "<html><body><video width='100%' height='100%' controls autoplay><source src='"
                + getBrowserstackVideoUrl(jsonPath)
                + "' type='video/mp4'></video></body></html>";
    }

    public static String getSessionId()
    {
        return ((RemoteWebDriver) getWebDriver()).getSessionId().toString();
    }

    public static String getBrowserstackVideoUrl(JsonPath jsonPath)
    {
        return jsonPath.get("automation_session.video_url");
    }

    public static JsonPath getBrowserStackResponse(String sessionId)
    {
        Response response = given()
                .auth().basic(capabilities_vars.get("browserstack.user"), capabilities_vars.get("browserstack.key"))
                .when()
                .get("https://api-cloud.browserstack.com/app-automate/sessions/" + sessionId + ".json")
                .then()
                .statusCode(200)
                .extract().response();
        return new JsonPath(response.asString());
    }

    public static String getBrowserstackDeviceLogsURL(JsonPath jsonPath)
    {
        return jsonPath.get("automation_session.device_logs_url").toString();
    }

    public static void getDeviceLogs(String url)
    {
        Response r = given()
                .auth().basic(capabilities_vars.get("browserstack.user"), capabilities_vars.get("browserstack.key"))
                .log().uri()
                .when()
                .get(url);
        if (r.getStatusCode() != 200 && counter <= 10)
        {
            sleep(10000);
            getDeviceLogs(url);
            counter++;
            System.out.println(counter);
        }
        else
        {
            response = r;
        }
    }
}
