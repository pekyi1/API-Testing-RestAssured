package Albums;

import java.util.HashMap;
import java.util.Map;

/**
 * Provides test data for Album-related API tests.
 */
public class AlbumDataProvider {

    public static Map<String, Object> createAlbumData(int userId, String title) {
        Map<String, Object> albumData = new HashMap<>();
        albumData.put("userId", userId);
        albumData.put("title", title);
        return albumData;
    }

    public static Map<String, Object> defaultCreateAlbumData() {
        return createAlbumData(1, "My new album");
    }

    public static Map<String, Object> defaultUpdateAlbumData() {
        return createAlbumData(1, "Updated album title");
    }

    public static int getExistingAlbumId() {
        return 1;
    }

    public static int getAlbumIdToUpdate() {
        return 1;
    }

    public static int getAlbumIdToDelete() {
        return 1;
    }

    public static int getAlbumIdForPhotos() {
        return 1;
    }
}
