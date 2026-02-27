package com.example.api;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ApiTests extends BaseTest {

    @Test
    @DisplayName("GET: Fetch users list and validate response")
    public void testGetUsers() {
        given()
                .when()
                .get("/users")
                .then()
                .statusCode(200)
                .header("Content-Type", containsString("application/json"))
                .body("", not(emptyArray()))
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schema/users-schema.json")).log().all();
    }

    @Test
    @DisplayName("POST: Create a user and validate status")
    public void testCreateUser() {
        String payload = "{\n" +
                "    \"name\": \"morpheus\",\n" +
                "    \"username\": \"morpheus\",\n" +
                "    \"email\": \"morpheus@example.com\"\n" +
                "}";

        given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post("/users")
                .then()
                .statusCode(201)
                .header("Content-Type", containsString("application/json"))
                .body("name", equalTo("morpheus"))
                .body("id", notNullValue());
    }

    @Test
    @DisplayName("PUT: Update user and validate change")
    public void testUpdateUser() {
        String payload = "{\n" +
                "    \"name\": \"morpheus\",\n" +
                "    \"email\": \"zion@example.com\"\n" +
                "}";

        given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .put("/users/2")
                .then()
                .statusCode(200)
                .body("email", equalTo("zion@example.com"));
    }

    @Test
    @DisplayName("DELETE: Delete user and validate status")
    public void testDeleteUser() {
        given()
                .when()
                .delete("/users/2")
                .then()
                .statusCode(200);
    }
}
