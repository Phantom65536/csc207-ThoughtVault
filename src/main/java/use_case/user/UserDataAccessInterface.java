package use_case.user;

import entity.User;

public interface UserDataAccessInterface {
    User getUserByUsername(String username);
    void save(User user);
    int getNewID();
}
