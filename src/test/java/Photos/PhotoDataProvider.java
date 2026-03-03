package Photos;

import java.util.HashMap;
import java.util.Map;

/**
 * Provides test data for Photo-related API tests.
 */
public class PhotoDataProvider {

    public static Map<String, Object> createPhotoData(int albumId, String title, String url, String thumbnailUrl) {
        Map<String, Object> photoData = new HashMap<>();
        photoData.put("albumId", albumId);
        photoData.put("title", title);
        photoData.put("url", url);
        photoData.put("thumbnailUrl", thumbnailUrl);
        return photoData;
    }

    public static Map<String, Object> defaultCreatePhotoData() {
        return createPhotoData(1, "Test photo", "https://via.placeholder.com/600/92c952",
                "https://via.placeholder.com/150/92c952");
    }

    public static Map<String, Object> defaultUpdatePhotoData() {
        return createPhotoData(1, "Updated photo title", "https://via.placeholder.com/600/updated",
                "https://via.placeholder.com/150/updated");
    }

    public static int getExistingPhotoId() {
        return 1;
    }

    public static int getPhotoIdToUpdate() {
        return 1;
    }

    public static int getPhotoIdToDelete() {
        return 1;
    }

    public static int getAlbumIdForPhotos() {
        return 1;
    }
}
