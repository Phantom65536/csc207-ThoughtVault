package entity;

public interface CredentialsInterface {
    String getUsername();
    byte[] getHashedPassword();
    CredentialsInterface getGoogleCredentials();
}
