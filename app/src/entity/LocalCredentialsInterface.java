package entity;

public interface LocalCredentialsInterface {
    String getUsername();
    byte[] getHashedPassword();
    String getAPIKey();
}
