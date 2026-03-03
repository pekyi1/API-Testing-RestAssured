package Photos;

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

import static Photos.PhotoDataProvider.*;
import static Photos.PhotoEndpoint.*;
import static org.hamcrest.Matchers.*;

/**
 * CRUD tests for the /photos resource.
 */
@Epic("API Testing RESTAssured")
@Feature("Photos Endpoint")
public class PhotoTest extends BaseTest {

    @Test
    @Story("Get Photos")
    @Severity(SeverityLevel.NORMAL)
    @Description("This test validates the JSON schema of the /photos endpoint")
    @Tag("smoke")
    @DisplayName("GET: Fetch all photos and validate schema")
    public void testGetAllPhotos() {
        getAllPhotos().then()
                .statusCode(200)
                .header("Content-Type", containsString("application/json"))
                .body("", not(emptyArray()))
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/photos-schema.json"));
    }

    @ParameterizedTest(name = "GET: Fetch single photo by ID {0}")
    @Story("Get Single Photo")
    @Severity(SeverityLevel.NORMAL)
    @Description("This test fetches a photo by its ID and validates its properties")
    @MethodSource("Photos.PhotoDataProvider#getExistingPhotoId")
    public void testGetPhotoById(int photoId) {
        getPhotoById(photoId).then()
                .statusCode(200)
                .body("id", equalTo(photoId))
                .body("albumId", notNullValue())
                .body("title", notNullValue())
                .body("url", notNullValue())
                .body("thumbnailUrl", notNullValue());
    }

    @ParameterizedTest(name = "GET: Fetch photos filtered by albumId {0}")
    @Story("Get Photos By Album ID")
    @Severity(SeverityLevel.NORMAL)
    @Description("This test reads all photos linked to a specific Album parameter")
    @MethodSource("Photos.PhotoDataProvider#getAlbumIdForPhotos")
    public void testGetPhotosByAlbumId(int albumId) {
        getPhotosByAlbumId(albumId).then()
                .statusCode(200)
                .body("", not(emptyArray()))
                .body("[0].albumId", equalTo(albumId));
    }

    @Test
    @DisplayName("POST: Create a photo and validate response")
    @Story("Create Photo")
    @Severity(SeverityLevel.CRITICAL)
    @Description("This test creates a new photo and checks returning structure")
    @Tag("smoke")
    public void testCreatePhoto() {
        createPhoto(defaultCreatePhotoData()).then()
                .statusCode(201)
                .body("title", equalTo("Test photo"))
                .body("id", notNullValue());
    }

    @ParameterizedTest(name = "PUT: Update a photo ID {0}")
    @Story("Update Photo")
    @Severity(SeverityLevel.CRITICAL)
    @Description("This test modifies a photo and verifies changes")
    @MethodSource("Photos.PhotoDataProvider#getPhotoIdToUpdate")
    public void testUpdatePhoto(int photoId) {
        updatePhoto(photoId, defaultUpdatePhotoData()).then()
                .statusCode(200)
                .body("title", equalTo("Updated photo title"));
    }

    @ParameterizedTest(name = "DELETE: Delete a photo ID {0}")
    @Story("Delete Photo")
    @Severity(SeverityLevel.CRITICAL)
    @Description("This test executes DELETE to remove an existing photo resource")
    @MethodSource("Photos.PhotoDataProvider#getPhotoIdToDelete")
    public void testDeletePhoto(int photoId) {
        deletePhoto(photoId).then()
                .statusCode(200);
    }
}
