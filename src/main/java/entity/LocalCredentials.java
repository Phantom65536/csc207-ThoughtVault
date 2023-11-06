package entity;

public class LocalCredentials {
    private final User user;
    private final String username;
    private final byte[] password;

    public LocalCredentials(User user, String username, byte[] password) {
        this.user = user;
        this.username = username;
        this.password = password;
    }
}
