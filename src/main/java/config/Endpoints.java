package config;

/**
 * Centralised endpoint constants for all API resources.
 * Keeps paths in one place so tests and services never hard-code URLs.
 */
public final class Endpoints {

    private Endpoints() {
        /* utility class */ }

    // ── Users ───────────────────────────────────────────
    public static final String USERS = "/users";
    public static final String USER_BY_ID = "/users/{id}";

    // ── Posts ────────────────────────────────────────────
    public static final String POSTS = "/posts";
    public static final String POST_BY_ID = "/posts/{id}";
    public static final String POST_COMMENTS = "/posts/{id}/comments";

    // ── Comments ────────────────────────────────────────
    public static final String COMMENTS = "/comments";
    public static final String COMMENT_BY_ID = "/comments/{id}";

    // ── Todos ───────────────────────────────────────────
    public static final String TODOS = "/todos";
    public static final String TODO_BY_ID = "/todos/{id}";

    // ── Albums ──────────────────────────────────────────
    public static final String ALBUMS = "/albums";
    public static final String ALBUM_BY_ID = "/albums/{id}";
    public static final String ALBUM_PHOTOS = "/albums/{id}/photos";
}
