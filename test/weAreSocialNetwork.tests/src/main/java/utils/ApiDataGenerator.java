package utils;

import com.github.javafaker.Faker;
import models.api.Authorities;
import models.api.helpers.Category;
import models.api.requestModel.UserRequestModel;
import models.ui.UserUiModel;

public class ApiDataGenerator {

    private static Faker faker = new Faker();

    public static UserRequestModel createUser(boolean isAdmin) {
        String username = faker.name().firstName();
        String password = faker.internet().password();
        String email = faker.internet().emailAddress(username);
        Authorities authority = isAdmin ? Authorities.ROLE_ADMIN : Authorities.ROLE_USER;
        String professionId = String.valueOf(faker.number().numberBetween(101, 157));
        Category category = new Category();
        category.setName("CategoryName");
        category.setCategoryId(Integer.parseInt(professionId));
        String[] authorities = new String[] {authority.toString()};
        UserRequestModel userRequestModel = new UserRequestModel();
        userRequestModel.setAuthorities(authorities);
        userRequestModel.setCategory(category);
        userRequestModel.setUsername(username);
        userRequestModel.setPassword(password);
        userRequestModel.setConfirmPassword(password);
        userRequestModel.setEmail(email);

        return userRequestModel;
    }
}
