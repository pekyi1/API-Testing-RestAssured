package models.request;

/**
 * POJO representing the body sent when creating or updating a Todo.
 */
public class TodoRequest {

    private int userId;
    private String title;
    private boolean completed;

    public TodoRequest() {
    }

    public TodoRequest(int userId, String title, boolean completed) {
        this.userId = userId;
        this.title = title;
        this.completed = completed;
    }

    public int getUserId() {
        return userId;
    }

    public String getTitle() {
        return title;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
