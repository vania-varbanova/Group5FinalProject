package utils;

import com.github.javafaker.Faker;
import models.api.requestModel.PostRequestModel;
import models.api.requestModel.UserRequestModel;
import models.ui.UserUiModel;

public class UiDataGenerator extends BaseDataGenerator {
    public UserUiModel createUser(){
        String username = faker.name().firstName();
        String password = faker.internet().password();
        String email = faker.internet().emailAddress(username);
        String profession = String.valueOf(faker.number().numberBetween(101, 157));;

        UserUiModel userUiModel  = new UserUiModel();
        userUiModel.setUsername(username);
        userUiModel.setEmail(email);
        userUiModel.setPassword(password);
        userUiModel.setProfession(profession);
        return  userUiModel;
    }
}
