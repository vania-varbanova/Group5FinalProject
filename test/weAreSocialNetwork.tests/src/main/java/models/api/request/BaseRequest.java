package models.api.request;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import io.restassured.response.Response;
import org.openqa.selenium.Cookie;
import utils.ConfigPropertiesReader;
import utils.ConsoleLogger;

import static org.junit.jupiter.api.Assertions.assertEquals;

public abstract class BaseRequest {
    protected final Gson jsonParser;
    protected final ConsoleLogger logger;

    protected BaseRequest() {
        EncoderConfig encoderConfig = RestAssured.config().getEncoderConfig()
                .appendDefaultContentCharsetToContentTypeIfUndefined(false);

        RestAssured.config = RestAssured.config().encoderConfig(encoderConfig);
        RestAssured.baseURI = ConfigPropertiesReader.getValueByKey("weAreSocialNetwork.api.baseUrl");
        jsonParser = new GsonBuilder().setPrettyPrinting().create();
        logger = new ConsoleLogger();
    }

    protected String generateAuthenticationCookieWithValue(String value) {
        var cookie = new Cookie("JSESSIONID", value, "/");
        return String.valueOf(cookie);
    }

    protected void assertSuccessStatusCode(Response response) {
        assertEquals(200, response.getStatusCode());
    }
}
