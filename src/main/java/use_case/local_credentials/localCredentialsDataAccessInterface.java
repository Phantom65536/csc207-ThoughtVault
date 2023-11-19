package use_case.local_credentials;

import com.google.api.client.auth.oauth2.Credential;
import entity.LocalCredentials;
import entity.User;

public interface localCredentialsDataAccessInterface {
    boolean existsByName(String identifier);
    void save(User user);
    User getUserByUsername(String username);
    String getUsername();
    byte[] getHashedPassword();
    Credential getCredential();
}
