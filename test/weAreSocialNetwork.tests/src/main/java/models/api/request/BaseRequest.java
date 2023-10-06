package models.api.request;

import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import testFramework.Utils;
import utils.ConfigPropertiesReader;

public abstract class BaseRequest {
    protected Gson jsonParser;
    public  BaseRequest() {
//        EncoderConfig encoderConfig = RestAssured.config().getEncoderConfig()
//                .appendDefaultContentCharsetToContentTypeIfUndefined(false);

//        RestAssured.config = RestAssured.config().encoderConfig(encoderConfig);
//        RestAssured.baseURI = ConfigPropertiesReader.getValueByKey("weAreSocialNetwork.api.baseUrl");
//        RestAssured.basePath = ConfigPropertiesReader.getValueByKey("weAreSocialNetwork.api.basePath");
        jsonParser = new Gson();
    }
}
