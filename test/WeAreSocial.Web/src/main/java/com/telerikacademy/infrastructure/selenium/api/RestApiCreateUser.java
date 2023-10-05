package com.telerikacademy.infrastructure.selenium.api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import com.github.javafaker.Faker;

public class RestApiCreateUser {

    public static void main(String[] args) {

        RestAssured.baseURI = "http://localhost:8081/api";

        Faker faker = new Faker();
        String professionId = String.valueOf(faker.number().numberBetween(101, 157));
        String email = faker.internet().emailAddress();
        String password = faker.internet().password();
        String username = faker.name().username().replaceAll("[^a-zA-Z]", "");


        String jsonPayload = "{\n" +
                "  \"authorities\": [\"ROLE_USER\"],\n" +
                "  \"category\": {\n" +
                "    \"id\": " + professionId + ",\n" +
                "    \"name\": \"CategoryName\"\n" +
                "  },\n" +
                "  \"confirmPassword\": \"" + password + "\",\n" +
                "  \"email\": \"" + email + "\",\n" +
                "  \"password\": \"" + password + "\",\n" +
                "  \"username\": \"" + username + "\"\n" +
                "}";


        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(jsonPayload)
                .post("/users/");


        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asString());
    }
}

