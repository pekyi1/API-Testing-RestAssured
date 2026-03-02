package assertions;

import io.restassured.response.Response;

import static org.hamcrest.Matchers.*;

/**
 * Reusable assertion helpers for Post-related API responses.
 */
public class PostAssertions {

    public static void assertPostListIsNotEmpty(Response response) {
        response.then()
                .statusCode(200)
                .body("", not(emptyArray()));
    }

    public static void assertPostById(Response response, int expectedId) {
        response.then()
                .statusCode(200)
                .body("id", equalTo(expectedId))
                .body("userId", notNullValue())
                .body("title", notNullValue());
    }

    public static void assertPostCreated(Response response, String expectedTitle) {
        response.then()
                .statusCode(201)
                .body("title", equalTo(expectedTitle))
                .body("id", notNullValue());
    }

    public static void assertPostUpdated(Response response, String expectedTitle) {
        response.then()
                .statusCode(200)
                .body("title", equalTo(expectedTitle));
    }

    public static void assertPostDeleted(Response response) {
        response.then()
                .statusCode(200);
    }
}
