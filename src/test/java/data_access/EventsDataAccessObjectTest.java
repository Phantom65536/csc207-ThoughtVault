package data_access;

import entity.localEvent.LocalEvent;
import entity.note.Note;
import junit.framework.TestCase;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;

public class EventsDataAccessObjectTest extends TestCase {

    public void testSave() throws IOException, ParseException {
        String timeNow = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss.SSS").format(new java.util.Date());
        EventsDataAccessObject dao = new EventsDataAccessObject("./testEvent"+timeNow+".json");

        LocalEvent firstEvent = new LocalEvent(dao.getNewID(), "first", 0, LocalDate.parse("2023-11-09"), LocalTime.NOON, LocalTime.parse("20:00"),
                "TA guy's crib", "This is a description.", true, false, new ArrayList<>());
        dao.save(firstEvent);

        ArrayList<LocalEvent> events0 = dao.getAllUserEntries(0);
        LocalEvent event0 = events0.get(0);

        assertEquals(1,events0.size());
        assertEquals(0, event0.getUserID());
        assertEquals(1, event0.getID());
        assertEquals("first",event0.getTitle());
        assertEquals(LocalDate.parse("2023-11-09"),event0.getDate());
        assertEquals(LocalTime.NOON,event0.getStartEndTIme()[0]);
        assertEquals(LocalTime.parse("20:00"),event0.getStartEndTIme()[1]);
        assertEquals("TA guy's crib",event0.getLocation());
        assertEquals("This is a description.",event0.getDescription());
        assertEquals(true, event0.isWork());
        assertEquals(false,event0.getPinned());
        assertEquals(0,event0.getDescendants().size());


        EventsDataAccessObject dao2 = new EventsDataAccessObject("./testEvent"+timeNow+".json");

        ArrayList<LocalEvent> events01 = dao2.getAllUserEntries(0);
        LocalEvent event01 = events01.get(0);

        assertEquals(1,events01.size());
        assertEquals(0, event01.getUserID());
        assertEquals(1, event01.getID());
        assertEquals("first",event01.getTitle());
        assertEquals(LocalDate.parse("2023-11-09"),event01.getDate());
        assertEquals(LocalTime.NOON,event01.getStartEndTIme()[0]);
        assertEquals(LocalTime.parse("20:00"),event01.getStartEndTIme()[1]);
        assertEquals("TA guy's crib",event01.getLocation());
        assertEquals("This is a description.",event01.getDescription());
        assertEquals(true, event01.isWork());
        assertEquals(false,event01.getPinned());
        assertEquals(0,event01.getDescendants().size());
    }

    public void testRepeatId() throws IOException, ParseException {
        String timeNow = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss.SSS").format(new java.util.Date());
        EventsDataAccessObject dao = new EventsDataAccessObject("./testEvent"+timeNow+".json");

        LocalEvent firstEvent = new LocalEvent(0, "first", 0, LocalDate.parse("2023-11-09"), LocalTime.NOON, LocalTime.parse("20:00"),
                "TA guy's crib", "This is a description.", true, false, new ArrayList<>());
        dao.save(firstEvent);
        dao.save(new LocalEvent(0, "second", 0, LocalDate.parse("2022-01-02"), LocalTime.MIDNIGHT, LocalTime.parse("11:00"),
                "TA guy's toilet", "There is no way this is not a description.", true, true, new ArrayList<>(Arrays.asList(firstEvent.getID(), 1000))));

        ArrayList<LocalEvent> events0 = dao.getAllUserEntries(0);
        LocalEvent event0 = events0.get(0);

        assertEquals(1,events0.size());
        assertEquals(0, event0.getUserID());
        assertEquals(0, event0.getID());
        assertEquals("second",event0.getTitle());
        assertEquals(LocalDate.parse("2022-01-02"),event0.getDate());
        assertEquals(LocalTime.MIDNIGHT,event0.getStartEndTIme()[0]);
        assertEquals(LocalTime.parse("11:00"),event0.getStartEndTIme()[1]);
        assertEquals("TA guy's toilet",event0.getLocation());
        assertEquals("There is no way this is not a description.",event0.getDescription());
        assertEquals(true, event0.isWork());
        assertEquals(true,event0.getPinned());
        assertEquals(2,event0.getDescendants().size());
    }
    public void testEvents() throws IOException, ParseException {
        String timeNow = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss.SSS").format(new java.util.Date());
        EventsDataAccessObject dao = new EventsDataAccessObject("./testEvent"+timeNow+".json");

        LocalEvent firstEvent = new LocalEvent(dao.getNewID(), "first", 0, LocalDate.parse("2023-11-09"), LocalTime.NOON, LocalTime.parse("20:00"),
                "TA guy's crib", "This is a description.", true, false, new ArrayList<>());
        dao.save(firstEvent);

        Note event0 = dao.getByID(1);
        assertEquals("first",event0.getTitle());

        dao.save(new LocalEvent(dao.getNewID(), "second", 0, LocalDate.parse("2022-01-02"), LocalTime.MIDNIGHT, LocalTime.parse("11:00"),
                "TA guy's toilet", "There is no way this is not a description.", true, true, new ArrayList<>(Arrays.asList(firstEvent.getID(), 1000))));

        LocalEvent event1 = dao.getByID(2);
        assertEquals(2,event1.getDescendants().size());

        dao.save(new LocalEvent(dao.getNewID(), "third", 0, LocalDate.parse("2022-01-02"), LocalTime.MIDNIGHT, LocalTime.parse("23:59"),
                "Garbage chute :)))", "NO DESCRIPTION T_T", false, true, new ArrayList<>(Arrays.asList(0, firstEvent.getID()))));
        Note event2 = dao.getByID(3);
        assertEquals(2,event2.getDescendants().size());

        LocalEvent otherUserEvent = new LocalEvent(dao.getNewID(), "another user", 1, LocalDate.parse("2022-01-02"), LocalTime.MIDNIGHT, LocalTime.parse("23:59"),
                "Garbage chute :)))", "NO DESCRIPTION T_T", false, true, new ArrayList<>());
        dao.save(otherUserEvent);
        LocalEvent event3 = dao.getByID(4);
        assertEquals("another user",event3.getTitle());

        otherUserEvent.amendAllAttributes("another user editted", LocalDate.parse("2022-01-02"), LocalTime.MIDNIGHT, LocalTime.parse("23:59"),
                "Garbage chute :(((", "EDITED DESCRIPTION T_T", true, true, new ArrayList<>());
        dao.save(otherUserEvent);
        LocalEvent event4 = dao.getByID(4);
        assertEquals("another user editted",event4.getTitle());

        ArrayList<LocalEvent> user0Events = dao.getAllUserEntries(0);
        assertEquals(3,user0Events.size());
        ArrayList<LocalEvent> user1Events = dao.getAllUserEntries(1);
        assertEquals(1,user1Events.size());

        dao.delete(firstEvent.getID());
        assertNull(dao.getByID(firstEvent.getID()));
        assertEquals(2,dao.getAllUserEntries(0).size());
        assertEquals(1,dao.getByID(2).getDescendants().size());
        assertEquals(1,dao.getByID(3).getDescendants().size());
    }

}