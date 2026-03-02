package tests.album;

import assertions.AlbumAssertions;
import base.BaseTest;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import models.request.AlbumRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import services.AlbumService;

import static org.hamcrest.Matchers.*;

/**
 * CRUD tests for the /albums resource.
 */
public class AlbumTests extends BaseTest {

    private final AlbumService albumService = new AlbumService();

    @Test
    @DisplayName("GET: Fetch all albums and validate schema")
    public void testGetAllAlbums() {
        Response response = albumService.getAllAlbums();

        response.then()
                .statusCode(200)
                .header("Content-Type", containsString("application/json"))
                .body("", not(emptyArray()))
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/albums-schema.json"));
    }

    @Test
    @DisplayName("GET: Fetch single album by ID")
    public void testGetAlbumById() {
        Response response = albumService.getAlbumById(1);

        response.then()
                .statusCode(200)
                .body("id", equalTo(1))
                .body("userId", notNullValue())
                .body("title", notNullValue());
    }

    @Test
    @DisplayName("GET: Fetch photos for an album")
    public void testGetAlbumPhotos() {
        Response response = albumService.getAlbumPhotos(1);

        response.then()
                .statusCode(200)
                .body("", not(emptyArray()))
                .body("[0].albumId", equalTo(1));
    }

    @Test
    @DisplayName("POST: Create an album and validate response")
    public void testCreateAlbum() {
        AlbumRequest payload = new AlbumRequest(1, "My new album");
        Response response = albumService.createAlbum(payload);

        AlbumAssertions.assertAlbumCreated(response, "My new album");
    }

    @Test
    @DisplayName("PUT: Update an album and validate change")
    public void testUpdateAlbum() {
        AlbumRequest payload = new AlbumRequest(1, "Updated album title");
        Response response = albumService.updateAlbum(1, payload);

        AlbumAssertions.assertAlbumUpdated(response, "Updated album title");
    }

    @Test
    @DisplayName("DELETE: Delete an album and validate status")
    public void testDeleteAlbum() {
        Response response = albumService.deleteAlbum(1);

        AlbumAssertions.assertAlbumDeleted(response);
    }
}
