package entity;

import com.google.api.client.auth.oauth2.Credential;

public class LocalCredentials {
    private final User user;
    private final String username;
    private final byte[] password;
    private final Credential credential;

    public LocalCredentials(User user, String username, byte[] password, Credential credential) {
        this.user = user;
        this.username = username;
        this.password = password;
        this.credential = credential;
    }

    public User getUser() {
        return user;
    }

    public String getUsername() {
        return username;
    }

    public byte[] getPassword() {
        return password;
    }

    public Credential getCredential() {
        return credential;
    }

    public boolean existsCredential() {
        return credential != null;
    }
}
