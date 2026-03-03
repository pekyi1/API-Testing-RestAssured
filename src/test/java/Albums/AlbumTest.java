package Albums;

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

import static Albums.AlbumDataProvider.*;
import static Albums.AlbumEndpoint.*;
import static org.hamcrest.Matchers.*;

/**
 * CRUD tests for the /albums resource.
 */
@Epic("API Testing RESTAssured")
@Feature("Albums Endpoint")
public class AlbumTest extends BaseTest {

    @Test
    @Story("Get Albums")
    @Severity(SeverityLevel.NORMAL)
    @Description("This test validates the JSON schema of the /albums endpoint")
    @Tag("smoke")
    @DisplayName("GET: Fetch all albums and validate schema")
    public void testGetAllAlbums() {
        getAllAlbums().then()
                .statusCode(200)
                .header("Content-Type", containsString("application/json"))
                .body("", not(emptyArray()))
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/albums-schema.json"));
    }

    @ParameterizedTest(name = "GET: Fetch single album by ID {0}")
    @Story("Get Single Album")
    @Severity(SeverityLevel.NORMAL)
    @Description("This test fetches an album by its ID and validates the response object")
    @MethodSource("Albums.AlbumDataProvider#getExistingAlbumId")
    public void testGetAlbumById(int albumId) {
        getAlbumById(albumId).then()
                .statusCode(200)
                .body("id", equalTo(albumId))
                .body("userId", notNullValue())
                .body("title", notNullValue());
    }

    @ParameterizedTest(name = "GET: Fetch photos for an album ID {0}")
    @Story("Get Album Photos")
    @Severity(SeverityLevel.NORMAL)
    @Description("This test verifies that photos mapped to a given album ID are returned")
    @MethodSource("Albums.AlbumDataProvider#getAlbumIdForPhotos")
    public void testGetAlbumPhotos(int albumId) {
        getAlbumPhotos(albumId).then()
                .statusCode(200)
                .body("", not(emptyArray()))
                .body("[0].albumId", equalTo(albumId));
    }

    @Test
    @DisplayName("POST: Create an album and validate response")
    @Story("Create Album")
    @Severity(SeverityLevel.CRITICAL)
    @Description("This test creates a new album and verifies that it is returned correctly")
    @Tag("smoke")
    public void testCreateAlbum() {
        createAlbum(defaultCreateAlbumData()).then()
                .statusCode(201)
                .body("title", equalTo("My new album"))
                .body("id", notNullValue());
    }

    @ParameterizedTest(name = "PUT: Update an album ID {0}")
    @Story("Update Album")
    @Severity(SeverityLevel.CRITICAL)
    @Description("This test updates an existing album and verifies the modified data")
    @MethodSource("Albums.AlbumDataProvider#getAlbumIdToUpdate")
    public void testUpdateAlbum(int albumId) {
        updateAlbum(albumId, defaultUpdateAlbumData()).then()
                .statusCode(200)
                .body("title", equalTo("Updated album title"));
    }

    @ParameterizedTest(name = "DELETE: Delete an album ID {0}")
    @Story("Delete Album")
    @Severity(SeverityLevel.CRITICAL)
    @Description("This test deletes an album by ID and verifies the 200 HTTP status response")
    @MethodSource("Albums.AlbumDataProvider#getAlbumIdToDelete")
    public void testDeleteAlbum(int albumId) {
        deleteAlbum(albumId).then()
                .statusCode(200);
    }

    // --- Edge Case / Negative Tests ---

    @ParameterizedTest(name = "GET: Fetch album with invalid ID {0} should fail gracefully")
    @Story("Get Single Album")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Negative Test: Expect a 404 Not Found when passing invalid album IDs")
    @MethodSource("Albums.AlbumDataProvider#getInvalidAlbumIds")
    @Tag("edge-case")
    public void testGetAlbumByInvalidId(int albumId) {
        getAlbumById(albumId).then()
                .statusCode(404);
    }

    @Test
    @DisplayName("POST: Create an album missing required title field")
    @Story("Create Album")
    @Severity(SeverityLevel.NORMAL)
    @Description("Negative Test: Verify an attempt to create an album without a title is rejected")
    @Tag("edge-case")
    public void testCreateAlbumMissingTitle() {
        createAlbum(albumDataMissingTitle()).then()
                .statusCode(400);
    }
}
