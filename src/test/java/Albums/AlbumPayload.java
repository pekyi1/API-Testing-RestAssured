package Albums;

/**
 * POJO representing the request/response payload for an Album.
 */
public class AlbumPayload {

    private int id;
    private int userId;
    private String title;

    public AlbumPayload() {
    }

    public AlbumPayload(int userId, String title) {
        this.userId = userId;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
