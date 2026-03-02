package tests.user;

import assertions.UserAssertions;
import base.BaseTest;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import models.request.UserRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import services.UserService;
import utils.PayloadBuilder;

import static org.hamcrest.Matchers.*;

/**
 * CRUD tests for the /users resource.
 */
public class UserTests extends BaseTest {

    private final UserService userService = new UserService();

    @Test
    @DisplayName("GET: Fetch all users and validate schema")
    public void testGetAllUsers() {
        Response response = userService.getAllUsers();

        response.then()
                .statusCode(200)
                .header("Content-Type", containsString("application/json"))
                .body("", not(emptyArray()))
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/users-schema.json"))
                .log().all();
    }

    @Test
    @DisplayName("GET: Fetch single user by ID")
    public void testGetUserById() {
        Response response = userService.getUserById(1);

        response.then()
                .statusCode(200)
                .body("id", equalTo(1))
                .body("name", notNullValue())
                .body("email", notNullValue());
    }

    @Test
    @DisplayName("POST: Create a user and validate response")
    public void testCreateUser() {
        UserRequest payload = PayloadBuilder.createUser("morpheus", "morpheus", "morpheus@example.com");
        Response response = userService.createUser(payload);

        UserAssertions.assertUserCreated(response, "morpheus");
    }

    @Test
    @DisplayName("PUT: Update user and validate change")
    public void testUpdateUser() {
        UserRequest payload = PayloadBuilder.createUser("morpheus", "morpheus", "zion@example.com");
        Response response = userService.updateUser(2, payload);

        UserAssertions.assertUserUpdated(response, "zion@example.com");
    }

    @Test
    @DisplayName("DELETE: Delete user and validate status")
    public void testDeleteUser() {
        Response response = userService.deleteUser(2);

        UserAssertions.assertUserDeleted(response);
    }
}
