package models.request;

/**
 * POJO representing the body sent when creating or updating a Post.
 */
public class PostRequest {

    private int userId;
    private String title;
    private String body;

    public PostRequest() {
    }

    public PostRequest(int userId, String title, String body) {
        this.userId = userId;
        this.title = title;
        this.body = body;
    }

    public int getUserId() {
        return userId;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
