package use_case.user;

import entity.User;

import java.util.HashMap;

public interface userDataAccessInterface {
    User getUserById(int userId);
    User getUserByName(String username);
    boolean existsByName(String identifier);
    void saveUser(User user);
    HashMap<String, Object> getAllAttributes();
}
