package data_access;

import entity.Note;
import junit.framework.TestCase;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;

public class NotesDataAccessObjectTest extends TestCase {



    public void testSave() throws IOException, ParseException {
        String timeNow = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss.SSS").format(new java.util.Date());
        NotesDataAccessObject dao = new NotesDataAccessObject("./testNotes"+timeNow+".json");

        Note firstNote = new Note(dao.getNewID(), "first", 0,
                "TA guy's crib", "This is a description.", true, false, new ArrayList<>());
        dao.save(firstNote);

        ArrayList<Note> events0 = dao.getAllUserEntries(0);
        Note event0 = events0.get(0);

        assertEquals(1,events0.size());
        assertEquals(0, event0.getUserID());
        assertEquals("first",event0.getTitle());
        assertEquals("TA guy's crib",event0.getLocation());
        assertEquals("This is a description.",event0.getDescription());
        assertEquals(true, event0.isWork());
        assertEquals(false,event0.getPinned());
        assertEquals(0,event0.getDescendants().size());


        NotesDataAccessObject dao2 = new NotesDataAccessObject("./testNotes"+timeNow+".json");

        ArrayList<Note> events01 = dao2.getAllUserEntries(0);
        Note event01 = events01.get(0);

        assertEquals(1,events01.size());
        assertEquals(0, event01.getUserID());
        assertEquals("first",event01.getTitle());
        assertEquals("TA guy's crib",event01.getLocation());
        assertEquals("This is a description.",event01.getDescription());
        assertEquals(true, event01.isWork());
        assertEquals(false,event01.getPinned());
        assertEquals(0,event01.getDescendants().size());
    }

    public void testRepeatId() throws IOException, ParseException {
        String timeNow = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss.SSS").format(new java.util.Date());
        NotesDataAccessObject dao = new NotesDataAccessObject("./testNotes"+timeNow+".json");

        Note firstNote = new Note(0, "first", 0,
                "TA guy's crib", "This is a description.", true, false, new ArrayList<>());
        dao.save(firstNote);
        dao.save(new Note(0, "second", 0,
                "TA guy's toilet", "There is no way this is not a description.", true, true, new ArrayList<>(Arrays.asList(firstNote.getID(), 1000))));

        ArrayList<Note> events0 = dao.getAllUserEntries(0);
        Note event0 = events0.get(0);

        assertEquals(1,events0.size());
        assertEquals(0, event0.getUserID());
        assertEquals("second",event0.getTitle());
        assertEquals("TA guy's toilet",event0.getLocation());
        assertEquals("There is no way this is not a description.",event0.getDescription());
        assertEquals(true, event0.isWork());
        assertEquals(true,event0.getPinned());
        assertEquals(2,event0.getDescendants().size());
    }

    @org.junit.Test
    public void testNotes() throws IOException, ParseException {
        String timeNow = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss.SSS").format(new java.util.Date());
        NotesDataAccessObject dao = new NotesDataAccessObject("./testNotes"+timeNow+".json");

        Note firstNote = new Note(dao.getNewID(), "first", 0,
                "TA guy's crib", "This is a description.", true, false, new ArrayList<>());
        dao.save(firstNote);

        dao.save(new Note(dao.getNewID(), "second", 0,
                "TA guy's toilet", "There is no way this is not a description.", true, true, new ArrayList<>(Arrays.asList(firstNote.getID(), 1000))));

        Note event1 = dao.getAllUserEntries(0).get(1);
        assertEquals(2,event1.getDescendants().size());

        dao.save(new Note(dao.getNewID(), "third", 0,
                "Garbage chute :)))", "NO DESCRIPTION T_T", false, true, new ArrayList<>(Arrays.asList(0, firstNote.getID()))));
        Note event2 = dao.getAllUserEntries(0).get(2);
        assertEquals(2,event2.getDescendants().size());

        Note otherUserEvent = new Note(dao.getNewID(), "another user", 1,
                "Garbage chute :)))", "NO DESCRIPTION T_T", false, true, new ArrayList<>());
        dao.save(otherUserEvent);

        otherUserEvent.amendAllAttributes("another user edited",
                "Garbage chute :(((", "EDITED DESCRIPTION T_T", true, true, new ArrayList<>());
        dao.save(otherUserEvent);

        Note event3 = dao.getAllUserEntries(1).get(0);
        assertEquals(1, event3.getUserID());
        assertEquals("another user edited",event3.getTitle());
        assertEquals("Garbage chute :(((",event3.getLocation());
        assertEquals("EDITED DESCRIPTION T_T",event3.getDescription());
        assertEquals(true, event3.isWork());
        assertEquals(true,event3.getPinned());
        assertEquals(0,event3.getDescendants().size());

        ArrayList<Note> user0Events = dao.getAllUserEntries(0);
        assertEquals(3,user0Events.size());
        ArrayList<Note> user1Events = dao.getAllUserEntries(1);
        assertEquals(1,user1Events.size());

        dao.delete(firstNote.getID());
        assertNull(dao.getByID(firstNote.getID()));

    }

}