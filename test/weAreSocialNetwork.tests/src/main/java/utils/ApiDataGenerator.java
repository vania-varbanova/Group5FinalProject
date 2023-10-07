package utils;

import com.github.javafaker.Faker;
import models.api.Authorities;
import models.api.helpers.Category;
import models.api.request.CommentRequestModel;
import models.api.requestModel.PostRequestModel;
import models.api.requestModel.UserRequestModel;
import models.ui.UserUiModel;

public class ApiDataGenerator {

    private Faker faker = new Faker();
    public UserRequestModel createUserWithRoleAdmin() {
        UserRequestModel adminUser = createUser(true);
        return adminUser;
    }

    public CommentRequestModel createComment(String userId, String postId){
        String commentContent = faker.lorem().characters(10,15);
        CommentRequestModel commentRequestModel = new CommentRequestModel();
        commentRequestModel.setContent(commentContent);
        commentRequestModel.setUserId(userId);
        commentRequestModel.setPostId(postId);
        return null;
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
        String postContent = faker.lorem().characters(10,20);
        String postPicture = faker.avatar().image();
        PostRequestModel postRequestModel = new PostRequestModel();
        postRequestModel.setContent(postContent);
        postRequestModel.setPicture(postPicture);
        postRequestModel.setPublic(isPublic ? true : false);

        return postRequestModel;
    }
}
