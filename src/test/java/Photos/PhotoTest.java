package Photos;

import config.BaseTest;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static Photos.PhotoDataProvider.*;
import static Photos.PhotoEndpoint.*;
import static org.hamcrest.Matchers.*;

/**
 * CRUD tests for the /photos resource.
 */
public class PhotoTest extends BaseTest {

    @Test
    @DisplayName("GET: Fetch all photos and validate schema")
    public void testGetAllPhotos() {
        getAllPhotos().then()
                .statusCode(200)
                .header("Content-Type", containsString("application/json"))
                .body("", not(emptyArray()))
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/photos-schema.json"));
    }

    @Test
    @DisplayName("GET: Fetch single photo by ID")
    public void testGetPhotoById() {
        getPhotoById(getExistingPhotoId()).then()
                .statusCode(200)
                .body("id", equalTo(getExistingPhotoId()))
                .body("albumId", notNullValue())
                .body("title", notNullValue())
                .body("url", notNullValue())
                .body("thumbnailUrl", notNullValue());
    }

    @Test
    @DisplayName("GET: Fetch photos filtered by albumId")
    public void testGetPhotosByAlbumId() {
        getPhotosByAlbumId(getAlbumIdForPhotos()).then()
                .statusCode(200)
                .body("", not(emptyArray()))
                .body("[0].albumId", equalTo(getAlbumIdForPhotos()));
    }

    @Test
    @DisplayName("POST: Create a photo and validate response")
    public void testCreatePhoto() {
        createPhoto(defaultCreatePhotoData()).then()
                .statusCode(201)
                .body("title", equalTo("Test photo"))
                .body("id", notNullValue());
    }

    @Test
    @DisplayName("PUT: Update a photo and validate change")
    public void testUpdatePhoto() {
        updatePhoto(getPhotoIdToUpdate(), defaultUpdatePhotoData()).then()
                .statusCode(200)
                .body("title", equalTo("Updated photo title"));
    }

    @Test
    @DisplayName("DELETE: Delete a photo and validate status")
    public void testDeletePhoto() {
        deletePhoto(getPhotoIdToDelete()).then()
                .statusCode(200);
    }
}
