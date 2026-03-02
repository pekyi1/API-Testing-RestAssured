package services;

import config.Endpoints;
import io.restassured.response.Response;
import utils.RestUtils;

/**
 * Service layer encapsulating all /albums API calls.
 */
public class AlbumService {

    public Response getAllAlbums() {
        return RestUtils.get(Endpoints.ALBUMS);
    }

    public Response getAlbumById(int id) {
        return RestUtils.getWithPathParam(Endpoints.ALBUM_BY_ID, "id", id);
    }

    public Response getAlbumPhotos(int albumId) {
        return RestUtils.getWithPathParam(Endpoints.ALBUM_PHOTOS, "id", albumId);
    }

    public Response createAlbum(Object body) {
        return RestUtils.post(Endpoints.ALBUMS, body);
    }

    public Response updateAlbum(int id, Object body) {
        return RestUtils.put(Endpoints.ALBUM_BY_ID, "id", id, body);
    }

    public Response deleteAlbum(int id) {
        return RestUtils.delete(Endpoints.ALBUM_BY_ID, "id", id);
    }
}
