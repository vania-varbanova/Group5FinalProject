package utils;

import com.github.javafaker.Faker;
import io.qameta.allure.Step;
import models.api.Authorities;
import models.api.helpers.Category;
import models.api.helpers.City;
import models.api.helpers.Location;
import models.api.requestModel.*;
import models.api.responseModel.ProfileManagementResponseModel;

import java.text.SimpleDateFormat;

public class ApiDataGenerator extends BaseDataGenerator {
    public UserRequestModel createUserWithRoleAdmin() {
        UserRequestModel adminUser = createUser(true);
        String adminName = "admin"+adminUser.getUsername();
        adminUser.setUsername(adminName);
        return adminUser;
    }
    @Step("Creating user request model")
    public UserRequestModel createUserWithRoleUser() {
        UserRequestModel user = createUser(false);
        return user;
    }

    private UserRequestModel createUser(boolean isAdmin) {
        String username = faker.name().firstName();
        String password = faker.internet().password();
        String email = faker.internet().emailAddress(username);
        Authorities authority = isAdmin ? Authorities.ROLE_ADMIN : Authorities.ROLE_USER;
        String professionId = String.valueOf(faker.number().numberBetween(101, 157));
        Category category = new Category();
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

    public CommentRequestModel createComment(String userId, String postId) {
        String commentContent = faker.lorem().characters(10, 15);
        CommentRequestModel commentRequestModel = new CommentRequestModel();
        commentRequestModel.setContentComment(commentContent);
        commentRequestModel.setUserId(userId);
        commentRequestModel.setPostId(postId);
        return commentRequestModel;

    }

    public PostRequestModel createPrivatePost() {
        var result = createPost(false);
        return result;
    }

    public PostRequestModel createPublicPost() {
        var result = createPost(true);
        return result;
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

    public SkillsRequestModel createSkill() {
        int categoryId = faker.number().numberBetween(101, 157);
        String skill = faker.job().keySkills();

        Category category = new Category();
        category.setCategoryId(categoryId);

        SkillsRequestModel skillsRequestModel = new SkillsRequestModel();
        skillsRequestModel.setCategory(category);
        skillsRequestModel.setSkill(skill);
        return skillsRequestModel;
    }

    public EditSkillRequestModel editSkill() {
        String skill = faker.job().keySkills();
        EditSkillRequestModel editSkillRequestModel = new EditSkillRequestModel();
        editSkillRequestModel.setSkill(skill);
        return editSkillRequestModel;
    }

    public ProfileManagementRequestModel updateProfile(boolean isMale) {
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
        profileManagementRequestModel.setBirthYear(birthYear);
        profileManagementRequestModel.setFirstName(firstName);
        profileManagementRequestModel.setLastName(lastName);
        profileManagementRequestModel.setPicturePrivate(picturePrivacy);
        profileManagementRequestModel.setGender(gender);
        profileManagementRequestModel.setLocation(location);

        return profileManagementRequestModel;
    }

    public EditPostRequestModel editPost(boolean isPublic) {
        String postContent = faker.lorem().characters(5, 10);
        String postPicture = faker.avatar().image();
        EditPostRequestModel editPostRequestModel = new EditPostRequestModel();
        editPostRequestModel.setContent(postContent);
        editPostRequestModel.setPicture(postPicture);
        editPostRequestModel.setPublic(isPublic ? true : false);

        return editPostRequestModel;
    }

    public ConnectionSendRequestModel createConnection(String receiverId, String receiverUsername) {
        ConnectionSendRequestModel receiver = new ConnectionSendRequestModel();
        receiver.setReceiverId(receiverId);
        receiver.setReceiverUsername(receiverUsername);

        return receiver;
    }

    public FriendRequestAcceptRequestModel createAcceptFriendRequest(String receiverId, String requestId) {
        FriendRequestAcceptRequestModel friendRequestAcceptRequestModel = new FriendRequestAcceptRequestModel();
        friendRequestAcceptRequestModel.setRequestId(requestId);
        friendRequestAcceptRequestModel.setReceiverId(receiverId);
        return friendRequestAcceptRequestModel;
    }
}
