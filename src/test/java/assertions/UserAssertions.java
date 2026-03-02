package assertions;

import io.restassured.response.Response;

import static org.hamcrest.Matchers.*;

/**
 * Reusable assertion helpers for User-related API responses.
 */
public class UserAssertions {

    public static void assertUserListIsNotEmpty(Response response) {
        response.then()
                .statusCode(200)
                .body("", not(emptyArray()));
    }

    public static void assertUserCreated(Response response, String expectedName) {
        response.then()
                .statusCode(201)
                .body("name", equalTo(expectedName))
                .body("id", notNullValue());
    }

    public static void assertUserUpdated(Response response, String expectedEmail) {
        response.then()
                .statusCode(200)
                .body("email", equalTo(expectedEmail));
    }

    public static void assertUserDeleted(Response response) {
        response.then()
                .statusCode(200);
    }
}
