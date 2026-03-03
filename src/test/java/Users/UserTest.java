package Users;

import config.BaseTest;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static Users.UserDataProvider.*;
import static Users.UserEndpoint.*;
import static org.hamcrest.Matchers.*;

/**
 * CRUD tests for the /users resource.
 */
@Epic("API Testing RESTAssured")
@Feature("Users Endpoint")
public class UserTest extends BaseTest {

    @Test
    @Story("Get Users")
    @Severity(SeverityLevel.NORMAL)
    @Description("This test validates the JSON schema of the /users endpoint")
    @Tag("smoke")
    @DisplayName("GET: Fetch all users and validate schema")
    public void testGetAllUsers() {
        getAllUsers().then()
                .statusCode(200)
                .header("Content-Type", containsString("application/json"))
                .body("", not(emptyArray()))
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/users-schema.json"))
                .log().all();
    }

    @ParameterizedTest(name = "GET: Fetch single user by ID {0}")
    @Story("Get Single User")
    @Severity(SeverityLevel.NORMAL)
    @Description("This test fetches a user by their ID and checks standard responses")
    @MethodSource("Users.UserDataProvider#getExistingUserId")
    public void testGetUserById(int userId) {
        getUserById(userId).then()
                .statusCode(200)
                .body("id", equalTo(userId))
                .body("name", notNullValue())
                .body("email", notNullValue());
    }

    @Test
    @DisplayName("POST: Create a user and validate response")
    @Story("Create User")
    @Severity(SeverityLevel.CRITICAL)
    @Description("This test creates a new profile in the users endpoint")
    @Tag("smoke")
    public void testCreateUser() {
        createUser(defaultCreateUserData()).then()
                .statusCode(201)
                .body("name", equalTo("morpheus"))
                .body("id", notNullValue());
    }

    @ParameterizedTest(name = "PUT: Update user ID {0}")
    @Story("Update User")
    @Severity(SeverityLevel.CRITICAL)
    @Description("This test updates an existing user profile")
    @MethodSource("Users.UserDataProvider#getUserIdToUpdate")
    public void testUpdateUser(int userId) {
        updateUser(userId, defaultUpdateUserData()).then()
                .statusCode(200)
                .body("email", equalTo("zion@example.com"));
    }

    @ParameterizedTest(name = "DELETE: Delete user ID {0}")
    @Story("Delete User")
    @Severity(SeverityLevel.CRITICAL)
    @Description("This test evaluates user deletion and a successful HTTP 200 response code")
    @MethodSource("Users.UserDataProvider#getUserIdToDelete")
    public void testDeleteUser(int userId) {
        deleteUser(userId).then()
                .statusCode(200);
    }
}
