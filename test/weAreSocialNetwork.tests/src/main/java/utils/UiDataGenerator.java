package utils;

import models.ui.PersonalProfileUiModel;
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

    public PersonalProfileUiModel createPersonalProfile(boolean isMale) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String birthYear = sdf.format(faker.date().birthday());
        String gender = isMale ? "MALE" : "FEMALE";
        String email = faker.internet().emailAddress(firstName);
        String city = String.valueOf(faker.number().numberBetween(1, 39));


        PersonalProfileUiModel personalProfileUiModel = new PersonalProfileUiModel();
        personalProfileUiModel.setFirstName(firstName);
        personalProfileUiModel.setLastName(lastName);
        personalProfileUiModel.setBirthYear(birthYear);
        personalProfileUiModel.setGender(gender);
        personalProfileUiModel.setEmail(email);
        personalProfileUiModel.setCity(city);


        return personalProfileUiModel;
    }


}