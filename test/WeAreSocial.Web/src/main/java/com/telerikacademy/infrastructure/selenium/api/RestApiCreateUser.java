package com.telerikacademy.infrastructure.selenium.api;

import com.community.weare.Models.dto.UserDTO;
import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.List;

public class RestApiCreateUser {

    private String username;
    private String password;

    private RequestSpecification getRestAssured() {
        String baseUrl = "http://localhost:8081";
        return RestAssured
                .given()
                .contentType(ContentType.JSON)
                .log().all()
                .baseUri(baseUrl);
    }

    public void createUser() {
        UserDTO newUser = generateRandomUser();

        Response createUserResponse = createNewUser(newUser);
        System.out.println("Create User Status Code: " + createUserResponse.getStatusCode());
        System.out.println("Create User Response Body: " + createUserResponse.getBody().asString());
    }

    public void authenticateUser() {
        Response authenticateUserResponse = authenticateUser(username, password);
        System.out.println("Authenticate User Status Code: " + authenticateUserResponse.getStatusCode());
    }

    public UserDTO generateRandomUser() {
        Faker faker = new Faker();

        username = faker.name().username().replaceAll("[^a-zA-Z]", "");
        password = faker.internet().password();
        String email = faker.internet().emailAddress();

        List<String> authorities = List.of("ROLE_USER");

        UserDTO user = new UserDTO(username, password, authorities);
        user.setEmail(email);
        user.setConfirmPassword(password);

        return user;
    }

    public Response createNewUser(UserDTO user) {
        return getRestAssured()
                .body(user)
                .when()
                .post("/api/users/")
                .then()
                .extract()
                .response();
    }

    public Response authenticateUser(String username, String password) {
        return getRestAssured()
                .queryParam("username", username)
                .queryParam("password", password)
                .when()
                .post("/authenticate")
                .then()
                .extract()
                .response();
    }
}