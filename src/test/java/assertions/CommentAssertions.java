package assertions;

import io.restassured.response.Response;

import static org.hamcrest.Matchers.*;

/**
 * Reusable assertion helpers for Comment-related API responses.
 */
public class CommentAssertions {

    public static void assertCommentListIsNotEmpty(Response response) {
        response.then()
                .statusCode(200)
                .body("", not(emptyArray()));
    }

    public static void assertCommentCreated(Response response, String expectedName) {
        response.then()
                .statusCode(201)
                .body("name", equalTo(expectedName))
                .body("id", notNullValue());
    }

    public static void assertCommentUpdated(Response response, String expectedBody) {
        response.then()
                .statusCode(200)
                .body("body", equalTo(expectedBody));
    }

    public static void assertCommentDeleted(Response response) {
        response.then()
                .statusCode(200);
    }
}
