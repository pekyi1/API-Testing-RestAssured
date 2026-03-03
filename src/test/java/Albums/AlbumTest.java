package Albums;

import config.BaseTest;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static Albums.AlbumDataProvider.*;
import static Albums.AlbumEndpoint.*;
import static org.hamcrest.Matchers.*;

/**
 * CRUD tests for the /albums resource.
 */
public class AlbumTest extends BaseTest {

    @Test
    @DisplayName("GET: Fetch all albums and validate schema")
    public void testGetAllAlbums() {
        getAllAlbums().then()
                .statusCode(200)
                .header("Content-Type", containsString("application/json"))
                .body("", not(emptyArray()))
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/albums-schema.json"));
    }

    @Test
    @DisplayName("GET: Fetch single album by ID")
    public void testGetAlbumById() {
        getAlbumById(getExistingAlbumId()).then()
                .statusCode(200)
                .body("id", equalTo(getExistingAlbumId()))
                .body("userId", notNullValue())
                .body("title", notNullValue());
    }

    @Test
    @DisplayName("GET: Fetch photos for an album")
    public void testGetAlbumPhotos() {
        getAlbumPhotos(getAlbumIdForPhotos()).then()
                .statusCode(200)
                .body("", not(emptyArray()))
                .body("[0].albumId", equalTo(getAlbumIdForPhotos()));
    }

    @Test
    @DisplayName("POST: Create an album and validate response")
    public void testCreateAlbum() {
        createAlbum(defaultCreateAlbumData()).then()
                .statusCode(201)
                .body("title", equalTo("My new album"))
                .body("id", notNullValue());
    }

    @Test
    @DisplayName("PUT: Update an album and validate change")
    public void testUpdateAlbum() {
        updateAlbum(getAlbumIdToUpdate(), defaultUpdateAlbumData()).then()
                .statusCode(200)
                .body("title", equalTo("Updated album title"));
    }

    @Test
    @DisplayName("DELETE: Delete an album and validate status")
    public void testDeleteAlbum() {
        deleteAlbum(getAlbumIdToDelete()).then()
                .statusCode(200);
    }
}
