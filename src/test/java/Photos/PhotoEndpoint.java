package Photos;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

/**
 * Encapsulates all /photos API endpoint calls.
 */
public class PhotoEndpoint {

    private static final String PHOTOS = "/photos";
    private static final String PHOTO_BY_ID = "/photos/{id}";

    public static Response getAllPhotos() {
        return given()
                .when()
                .get(PHOTOS);
    }

    public static Response getPhotoById(int id) {
        return given()
                .pathParam("id", id)
                .when()
                .get(PHOTO_BY_ID);
    }

    public static Response getPhotosByAlbumId(int albumId) {
        return given()
                .queryParam("albumId", albumId)
                .when()
                .get(PHOTOS);
    }

    public static Response createPhoto(Object body) {
        return given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post(PHOTOS);
    }

    public static Response updatePhoto(int id, Object body) {
        return given()
                .contentType(ContentType.JSON)
                .pathParam("id", id)
                .body(body)
                .when()
                .put(PHOTO_BY_ID);
    }

    public static Response deletePhoto(int id) {
        return given()
                .pathParam("id", id)
                .when()
                .delete(PHOTO_BY_ID);
    }
}
