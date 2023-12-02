package use_case;

import entity.Note;

import java.util.ArrayList;

public interface EntriesDataAccessInterface<T extends Note> {
    T getByID(int entryID);
<<<<<<< HEAD

    ArrayList<T> getAllUserEntries(int userID);

    void save(T entry);

    void delete(int entryID);

    int getNewID();
}
=======
    ArrayList<T> getAllUserEntries(int userID);
    void save(T entry);
    void delete(int entryID);
    int getNewID();
}
>>>>>>> 8a370b815b9e81e3465ed189b97b54d70909ee1b
