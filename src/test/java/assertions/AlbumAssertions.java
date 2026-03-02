package assertions;

import io.restassured.response.Response;

import static org.hamcrest.Matchers.*;

/**
 * Reusable assertion helpers for Album-related API responses.
 */
public class AlbumAssertions {

    public static void assertAlbumListIsNotEmpty(Response response) {
        response.then()
                .statusCode(200)
                .body("", not(emptyArray()));
    }

    public static void assertAlbumCreated(Response response, String expectedTitle) {
        response.then()
                .statusCode(201)
                .body("title", equalTo(expectedTitle))
                .body("id", notNullValue());
    }

    public static void assertAlbumUpdated(Response response, String expectedTitle) {
        response.then()
                .statusCode(200)
                .body("title", equalTo(expectedTitle));
    }

    public static void assertAlbumDeleted(Response response) {
        response.then()
                .statusCode(200);
    }
}
