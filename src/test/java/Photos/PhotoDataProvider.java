package Photos;

import utils.TestDataUtils;

import java.util.Map;

import java.util.stream.Stream;

/**
 * Provides test data for Photo-related API tests.
 */
public class PhotoDataProvider {

    public static Map<String, Object> defaultCreatePhotoData() {
        return TestDataUtils.getTestData("photo.json");
    }

    public static Map<String, Object> defaultUpdatePhotoData() {
        Map<String, Object> data = TestDataUtils.getTestData("photo.json");
        data.put("title", "Updated photo title");
        data.put("url", "https://via.placeholder.com/600/updated");
        data.put("thumbnailUrl", "https://via.placeholder.com/150/updated");
        return data;
    }

    public static Stream<Integer> getExistingPhotoId() {
        return Stream.of(1, 2, 3);
    }

    public static Stream<Integer> getPhotoIdToUpdate() {
        return Stream.of(4, 5, 6);
    }

    public static Stream<Integer> getPhotoIdToDelete() {
        return Stream.of(7, 8, 9);
    }

    public static Stream<Integer> getAlbumIdForPhotos() {
        return Stream.of(1, 2, 3);
    }
}
