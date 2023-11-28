package use_case;

import entity.User;

public interface UserDataAccessInterface {
    User getUserByUsername(String username);
    void save(User user);
    int getNewID();
}
