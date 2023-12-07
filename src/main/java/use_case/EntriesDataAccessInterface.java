package use_case;

import entity.note.Note;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface EntriesDataAccessInterface<T extends Note> {
    T getByID(int entryID);
    ArrayList<T> getAllUserEntries(int userID);
    void save(T entry);
    void delete(int entryID);
    int getNewID();
    Map<Integer, String> getTitlesOfSubEntries(List<Integer> subEntries);
}