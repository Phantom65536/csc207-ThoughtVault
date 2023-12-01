package data_access;

import entity.NoteInterface;

import java.util.ArrayList;

public interface NoteDataAccessInterface {
    NoteInterface getByTitle(String title);
    ArrayList<NoteInterface> getAllUserNotes(String userID);
    void save(NoteInterface note);
    void delete(String title);
}
