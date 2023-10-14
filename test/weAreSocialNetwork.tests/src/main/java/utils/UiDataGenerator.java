package utils;

import com.github.javafaker.Faker;
import models.api.helpers.City;
import models.api.helpers.Location;
import models.api.requestModel.PostRequestModel;
import models.api.requestModel.ProfileManagementRequestModel;
import models.api.requestModel.UserRequestModel;
import models.ui.UpdateUserUiModel;
import models.ui.UserUiModel;

import java.text.SimpleDateFormat;

public class UiDataGenerator extends BaseDataGenerator {
    public UserUiModel createUser() {
        String username = faker.name().firstName();
        String password = faker.internet().password();
        String email = faker.internet().emailAddress(username);
        String profession = String.valueOf(faker.number().numberBetween(101, 157));

        UserUiModel userUiModel = new UserUiModel();
        userUiModel.setUsername(username);
        userUiModel.setEmail(email);
        userUiModel.setPassword(password);
        userUiModel.setConfirmationPassword(password);
        userUiModel.setProfession(profession);
        return userUiModel;
    }

    public UpdateUserUiModel updateUser(boolean isMale) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String birthYear = sdf.format(faker.date().birthday());
        String gender = isMale ? "MALE" : "FEMALE";
        String city = "Sofia";

        UpdateUserUiModel updateUserUiModel = new UpdateUserUiModel();


        return null;
    }


}