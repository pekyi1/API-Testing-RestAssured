package Albums;

import utils.TestDataUtils;

import java.util.Map;

import java.util.stream.Stream;

/**
 * Provides test data for Album-related API tests.
 */
public class AlbumDataProvider {

    public static Map<String, Object> defaultCreateAlbumData() {
        return TestDataUtils.getTestData("album.json");
    }

    public static Map<String, Object> defaultUpdateAlbumData() {
        Map<String, Object> data = TestDataUtils.getTestData("album.json");
        data.put("title", "Updated album title");
        return data;
    }

    public static Stream<Integer> getExistingAlbumId() {
        return Stream.of(1, 2, 3);
    }

    public static Stream<Integer> getAlbumIdToUpdate() {
        return Stream.of(4, 5, 6);
    }

    public static Stream<Integer> getAlbumIdToDelete() {
        return Stream.of(7, 8, 9);
    }

    public static Stream<Integer> getAlbumIdForPhotos() {
        return Stream.of(1, 2, 3);
    }

    // --- Data for Edge Cases / Negative Testing ---

    /**
     * Provides invalid IDs that do not exist or are malformed (e.g., negative, very
     * large)
     */
    public static Stream<Integer> getInvalidAlbumIds() {
        return Stream.of(-1, 0, 999999);
    }

    /** Provides data for an album missing the mandatory 'title' field */
    public static Map<String, Object> albumDataMissingTitle() {
        Map<String, Object> data = TestDataUtils.getTestData("album.json");
        data.remove("title");
        return data;
    }
}
