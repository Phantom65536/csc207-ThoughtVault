package entity;

public interface LocalCredentialsInterface {
    String getUsername();
    byte[] getHashedPassword();
}
