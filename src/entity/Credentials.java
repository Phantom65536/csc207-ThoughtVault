package entity;

public class Credentials implements CredentialsInterface {
    private final String username;
    private final byte[] hashedPassword;
    private final CredentialsInterface googleCredentials;

    public Credentials(String username, byte[] hashedPassword,
                       CredentialsInterface googleCredentials) {
        this.username = username;
        this.hashedPassword = hashedPassword;
        this.googleCredentials = googleCredentials;
    }

    public String getUsername() {
        return username;
    }

    public byte[] getHashedPassword() {
        return hashedPassword;
    }

    public CredentialsInterface getGoogleCredentials() {
        return googleCredentials;
    }
}
