package utils;

import com.github.javafaker.Faker;
import models.ui.UserUiModel;

public class UiDataGenerator {

    private static Faker faker = new Faker();

    public static UserUiModel createUser(){
        String username = faker.name().firstName();
        String password = faker.internet().password();
        String email = faker.internet().emailAddress(username);
        UserUiModel userUiModel = new UserUiModel();
        userUiModel.setEmail(email);
        userUiModel.setPassword(password);

        return userUiModel;
    }
}
