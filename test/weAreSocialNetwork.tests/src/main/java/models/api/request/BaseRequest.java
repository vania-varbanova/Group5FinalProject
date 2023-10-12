package models.api.request;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.Cookie;
import utils.ConfigPropertiesReader;

public abstract class BaseRequest {
    protected Gson jsonParser;

    public BaseRequest() {
        EncoderConfig encoderConfig = RestAssured.config().getEncoderConfig()
                .appendDefaultContentCharsetToContentTypeIfUndefined(false);

        RestAssured.config = RestAssured.config().encoderConfig(encoderConfig);
        RestAssured.baseURI = ConfigPropertiesReader.getValueByKey("weAreSocialNetwork.api.baseUrl");
        jsonParser = new GsonBuilder().setPrettyPrinting().create();
    }

    protected String generateAuthenticationCookieWithValue(String value) {
        // Скрива логиката зад генерирането на аутентикационното куки
        Cookie cookie = new Cookie("JSESSIONID", value, "/");
        return String.valueOf(cookie);
    }


    protected void assertSuccessStatusCode(Response response) {
        Assertions.assertEquals(200, response.getStatusCode());
    }
}
