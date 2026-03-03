package Users;

import config.BaseTest;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static Users.UserDataProvider.*;
import static Users.UserEndpoint.*;
import static org.hamcrest.Matchers.*;

/**
 * CRUD tests for the /users resource.
 */
public class UserTest extends BaseTest {

    @Test
    @DisplayName("GET: Fetch all users and validate schema")
    public void testGetAllUsers() {
        getAllUsers().then()
                .statusCode(200)
                .header("Content-Type", containsString("application/json"))
                .body("", not(emptyArray()))
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/users-schema.json"))
                .log().all();
    }

    @Test
    @DisplayName("GET: Fetch single user by ID")
    public void testGetUserById() {
        getUserById(getExistingUserId()).then()
                .statusCode(200)
                .body("id", equalTo(getExistingUserId()))
                .body("name", notNullValue())
                .body("email", notNullValue());
    }

    @Test
    @DisplayName("POST: Create a user and validate response")
    public void testCreateUser() {
        createUser(defaultCreateUserData()).then()
                .statusCode(201)
                .body("name", equalTo("morpheus"))
                .body("id", notNullValue());
    }

    @Test
    @DisplayName("PUT: Update user and validate change")
    public void testUpdateUser() {
        updateUser(getUserIdToUpdate(), defaultUpdateUserData()).then()
                .statusCode(200)
                .body("email", equalTo("zion@example.com"));
    }

    @Test
    @DisplayName("DELETE: Delete user and validate status")
    public void testDeleteUser() {
        deleteUser(getUserIdToDelete()).then()
                .statusCode(200);
    }
}
