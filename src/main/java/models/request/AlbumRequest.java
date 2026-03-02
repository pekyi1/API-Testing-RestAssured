package models.request;

/**
 * POJO representing the body sent when creating or updating an Album.
 */
public class AlbumRequest {

    private int userId;
    private String title;

    public AlbumRequest() {
    }

    public AlbumRequest(int userId, String title) {
        this.userId = userId;
        this.title = title;
    }

    public int getUserId() {
        return userId;
    }

    public String getTitle() {
        return title;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
