package assertions;

import io.restassured.response.Response;

import static org.hamcrest.Matchers.*;

/**
 * Reusable assertion helpers for Todo-related API responses.
 */
public class TodoAssertions {

    public static void assertTodoListIsNotEmpty(Response response) {
        response.then()
                .statusCode(200)
                .body("", not(emptyArray()));
    }

    public static void assertTodoCreated(Response response, String expectedTitle) {
        response.then()
                .statusCode(201)
                .body("title", equalTo(expectedTitle))
                .body("id", notNullValue());
    }

    public static void assertTodoUpdated(Response response, String expectedTitle) {
        response.then()
                .statusCode(200)
                .body("title", equalTo(expectedTitle));
    }

    public static void assertTodoDeleted(Response response) {
        response.then()
                .statusCode(200);
    }
}
