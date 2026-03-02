package utils;

import models.request.AlbumRequest;
import models.request.CommentRequest;
import models.request.PostRequest;
import models.request.TodoRequest;
import models.request.UserRequest;

/**
 * Factory methods for building request payloads used across tests.
 */
public class PayloadBuilder {

    public static UserRequest createUser(String name, String username, String email) {
        return new UserRequest(name, username, email);
    }

    public static PostRequest createPost(int userId, String title, String body) {
        return new PostRequest(userId, title, body);
    }

    public static CommentRequest createComment(int postId, String name, String email, String body) {
        return new CommentRequest(postId, name, email, body);
    }

    public static TodoRequest createTodo(int userId, String title, boolean completed) {
        return new TodoRequest(userId, title, completed);
    }

    public static AlbumRequest createAlbum(int userId, String title) {
        return new AlbumRequest(userId, title);
    }
}
