package Albums;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

/**
 * Encapsulates all /albums API endpoint calls.
 */
public class AlbumEndpoint {

    private static final String ALBUMS = "/albums";
    private static final String ALBUM_BY_ID = "/albums/{id}";
    private static final String ALBUM_PHOTOS = "/albums/{id}/photos";

    public static Response getAllAlbums() {
        return given()
                .when()
                .get(ALBUMS);
    }

    public static Response getAlbumById(int id) {
        return given()
                .pathParam("id", id)
                .when()
                .get(ALBUM_BY_ID);
    }

    public static Response getAlbumPhotos(int albumId) {
        return given()
                .pathParam("id", albumId)
                .when()
                .get(ALBUM_PHOTOS);
    }

    public static Response createAlbum(Object body) {
        return given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post(ALBUMS);
    }

    public static Response updateAlbum(int id, Object body) {
        return given()
                .contentType(ContentType.JSON)
                .pathParam("id", id)
                .body(body)
                .when()
                .put(ALBUM_BY_ID);
    }

    public static Response deleteAlbum(int id) {
        return given()
                .pathParam("id", id)
                .when()
                .delete(ALBUM_BY_ID);
    }
}
