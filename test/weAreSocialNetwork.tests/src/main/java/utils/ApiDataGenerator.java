package utils;

import com.github.javafaker.Faker;
import models.api.Authorities;
import models.api.helpers.Category;
import models.api.helpers.City;
import models.api.helpers.Location;
import models.api.requestModel.*;
import models.api.responseModel.ProfileManagementResponseModel;

import java.text.SimpleDateFormat;

public class ApiDataGenerator {

    private Faker faker = new Faker();

    public UserRequestModel createUserWithRoleAdmin() {
        UserRequestModel adminUser = createUser(true);
        return adminUser;
    }

    public CommentRequestModel createComment(String userId, String postId) {
        String commentContent = faker.lorem().characters(10, 15);
        CommentRequestModel commentRequestModel = new CommentRequestModel();
        commentRequestModel.setContentComment(commentContent);
        commentRequestModel.setUserId(userId);
        commentRequestModel.setPostId(postId);
        return commentRequestModel;

    }

    public UserRequestModel createUserWithRoleUser() {
        UserRequestModel user = createUser(false);
        return user;
    }

    public PostRequestModel createPrivatePost() {
        var result = createPost(false);
        return result;
    }

    public PostRequestModel createPublicPost() {
        var result = createPost(true);
        return result;
    }

    private UserRequestModel createUser(boolean isAdmin) {
        String username = faker.name().firstName();
        String password = faker.internet().password();
        String email = faker.internet().emailAddress(username);
        Authorities authority = isAdmin ? Authorities.ROLE_ADMIN : Authorities.ROLE_USER;
        String professionId = String.valueOf(faker.number().numberBetween(101, 157));
        Category category = new Category();
        category.setName("CategoryName");
        category.setCategoryId(Integer.parseInt(professionId));
        String[] authorities = new String[]{authority.toString()};
        UserRequestModel userRequestModel = new UserRequestModel();
        userRequestModel.setAuthorities(authorities);
        userRequestModel.setCategory(category);
        userRequestModel.setUsername(username);
        userRequestModel.setPassword(password);
        userRequestModel.setConfirmPassword(password);
        userRequestModel.setEmail(email);

        return userRequestModel;
    }

    private PostRequestModel createPost(boolean isPublic) {
        String postContent = faker.lorem().characters(10, 20);
        String postPicture = faker.avatar().image();
        PostRequestModel postRequestModel = new PostRequestModel();
        postRequestModel.setContent(postContent);
        postRequestModel.setPicture(postPicture);
        postRequestModel.setPublic(isPublic ? true : false);

        return postRequestModel;
    }

    public SkillsRequestModel creatSkill() {
        String professionId = String.valueOf(faker.number().numberBetween(101, 157));
        String skill = faker.job().keySkills();
        SkillsRequestModel skillsRequestModel = new SkillsRequestModel();
        skillsRequestModel.setProfessionID(professionId);
        skillsRequestModel.setSkill(skill);
        return skillsRequestModel;
    }

    public ProfileManagementRequestModel createProfile(boolean isMale) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String birthYear = sdf.format(faker.date().birthday());
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String locationId = String.valueOf(1);
        Location location = new Location();
        location.setLocationId(locationId);
        City city = new City();
        city.setCityId(String.valueOf(faker.number().numberBetween(1, 39)));
        location.setCity(city);
        boolean picturePrivacy = false;
        String gender = isMale ? "MALE" : "FEMALE";
        ProfileManagementRequestModel profileManagementRequestModel = new ProfileManagementRequestModel();
//        ProfileManagementRequestModel profileManagementRequestModel1 = ProfileManagementRequestModel.builder()
//                .firstName(firstName)
//                .birthYear(birthYear)
//                .build();
        profileManagementRequestModel.setBirthYear(birthYear);
        profileManagementRequestModel.setFirstName(firstName);
        profileManagementRequestModel.setLastName(lastName);

        profileManagementRequestModel.setPicturePrivate(picturePrivacy);
        profileManagementRequestModel.setGender(gender);
        profileManagementRequestModel.setLocation(location);

        return profileManagementRequestModel;
    }

    public EditPostRequestModel createEditPost(boolean isPublic) {
        String postContent = faker.lorem().characters(5, 10);
        String postPicture = faker.avatar().image();
        EditPostRequestModel editPostRequestModel = new EditPostRequestModel();
        editPostRequestModel.setContent(postContent);
        editPostRequestModel.setPicture(postPicture);
        editPostRequestModel.setPublic(isPublic ? true : false);

        return editPostRequestModel;
    }
}
