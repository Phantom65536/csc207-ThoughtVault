package use_case.localEvent;

import data_access.EventsDataAccessObject;
import entity.localEvent.LocalEvent;
import org.json.simple.parser.ParseException;
import org.junit.Test;
import org.mockito.Answers;
import org.mockito.ArgumentCaptor;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.Mockito.*;

public class LocalEventInteractorTest {

    public static  String timeNow = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss.SSS").format(new Date());
    public static LocalEventOutputBoundary LocalEventOutputBoundary = mock(LocalEventOutputBoundary.class, Answers.CALLS_REAL_METHODS);

    public static ArrayList<Integer> descendent = new ArrayList<>(Arrays.asList(1, 1000));

    public void compareLocalEvents(LocalEventInputData LocalEventInputData, LocalEvent event){
        assertEquals(LocalEventInputData.getTitle(), event.getTitle());
        assertEquals(LocalEventInputData.getUserID(), event.getUserID());
        assertEquals(LocalEventInputData.getLocation(), event.getLocation());
        assertEquals(LocalEventInputData.getDescription(), event.getDescription());
        assertEquals(LocalEventInputData.getDate(), event.getDate());
        assertEquals(LocalEventInputData.getStartTime(), event.getStartTime());
        assertEquals(LocalEventInputData.getEndTime(), event.getEndTime());
        assertEquals(LocalEventInputData.getIsWork(), event.isWork());
        assertEquals(LocalEventInputData.getPinned(), event.getPinned());
        assertEquals(LocalEventInputData.getSubEvents(), event.getDescendants());
    }
    public void compareLocalEvents1(LocalEventInputData LocalEventInputData, LocalEvent event){
        assertEquals(event.getID(), 1);
        assertEquals(LocalEventInputData.getTitle(), "first");
        assertEquals(LocalEventInputData.getUserID(), 0);
        assertEquals(LocalEventInputData.getLocation(), "TA guy's crib");
        assertEquals(LocalEventInputData.getDescription(), "This is a description.");
        assertEquals(LocalEventInputData.getDate(),LocalDate.parse("2023-11-09"));
        assertEquals(LocalEventInputData.getStartTime(), LocalTime.NOON);
        assertEquals(LocalEventInputData.getEndTime(), LocalTime.parse("20:00"));
        assertEquals(LocalEventInputData.getIsWork(), true);
        assertEquals(LocalEventInputData.getPinned(), false);
        assertEquals(LocalEventInputData.getSubEvents().size(), 0);

        assertEquals(event.getID(), 1);
        assertEquals(event.getTitle(), "first");
        assertEquals(event.getUserID(), 0);
        assertEquals(event.getLocation(), "TA guy's crib");
        assertEquals(event.getDescription(), "This is a description.");
        assertEquals(LocalEventInputData.getDate(), LocalDate.parse("2023-11-09"));
        assertEquals(LocalEventInputData.getStartTime(), LocalTime.NOON);
        assertEquals(LocalEventInputData.getEndTime(), LocalTime.parse("20:00"));
        assertEquals(event.isWork(), true);
        assertEquals(event.getPinned(), false);
        assertEquals(event.getDescendants().size(), 0);
    }

    public void compareLocalEvents1(LocalEventOutputData localEventOutputData){
        assertEquals(localEventOutputData.getID(), 1);
        assertEquals(localEventOutputData.getTitle(), "first");
        assertEquals(localEventOutputData.getUserId(), 0);
        assertEquals(localEventOutputData.getLocation(), "TA guy's crib");
        assertEquals(localEventOutputData.getDescription(), "This is a description.");
        assertEquals(localEventOutputData.getDate(),LocalDate.parse("2023-11-09"));
        assertEquals(localEventOutputData.getStartTime(), LocalTime.NOON);
        assertEquals(localEventOutputData.getEndTime(), LocalTime.parse("20:00"));
        assertEquals(localEventOutputData.getIsWork(), true);
        assertEquals(localEventOutputData.getPinned(), false);
        assertEquals(localEventOutputData.getSubEvents().size(), 0);
        HashMap<String, Object> eventData = localEventOutputData.getEventData();
        assertEquals(eventData.get("date"),LocalDate.parse("2023-11-09"));
        assertEquals(eventData.get("startTime"), LocalTime.NOON);
        assertEquals(eventData.get("endTime"), LocalTime.parse("20:00"));

    }

    public void compareLocalEvents2(LocalEventInputData LocalEventInputData, LocalEvent event){

        assertEquals(event.getID(), 2);
        assertEquals(LocalEventInputData.getTitle(), "second");
        assertEquals(LocalEventInputData.getUserID(), 0);
        assertEquals(LocalEventInputData.getLocation(), "TA guy's toilet");
        assertEquals(LocalEventInputData.getDescription(), "There is no way this is not a description.");
        assertEquals(LocalEventInputData.getDate(),LocalDate.parse("2022-01-02"));
        assertEquals(LocalEventInputData.getStartTime(), LocalTime.MIDNIGHT);
        assertEquals(LocalEventInputData.getEndTime(), LocalTime.parse("11:00"));
        assertEquals(LocalEventInputData.getIsWork(), true);
        assertEquals(LocalEventInputData.getPinned(), true);
        assertEquals(LocalEventInputData.getSubEvents().size(), 2);

        assertEquals(event.getID(), 2);
        assertEquals(event.getTitle(), "second");
        assertEquals(event.getUserID(), 0);
        assertEquals(event.getLocation(), "TA guy's toilet");
        assertEquals(event.getDescription(), "There is no way this is not a description.");
        assertEquals(LocalEventInputData.getDate(),LocalDate.parse("2022-01-02"));
        assertEquals(LocalEventInputData.getStartTime(), LocalTime.MIDNIGHT);
        assertEquals(LocalEventInputData.getEndTime(), LocalTime.parse("11:00"));
        assertEquals(event.isWork(), true);
        assertEquals(event.getPinned(), true);
        assertEquals(event.getDescendants().size(), 2);
    }


    @Test
    public void testCreateLocalEvent() throws IOException, ParseException {

        EventsDataAccessObject LocalEventsDataAccessObject = new EventsDataAccessObject("./testEvent"+timeNow+".json");
        LocalEventInteractor LocalEventInteractor =  new LocalEventInteractor(LocalEventsDataAccessObject, LocalEventOutputBoundary);

        LocalEventInputData LocalEventInputData = new LocalEventInputData(0, "first", 0, LocalDate.parse("2023-11-09"), LocalTime.NOON, LocalTime.parse("20:00"),
                "TA guy's crib", "This is a description.", true, false, new ArrayList<>());
        LocalEventInteractor.CreateEvent(LocalEventInputData);
        LocalEvent event = LocalEventsDataAccessObject.getByID(1);
        compareLocalEvents1(LocalEventInputData, event);
        compareLocalEvents(LocalEventInputData, event);
        ArrayList<LocalEvent> eventList = LocalEventsDataAccessObject.getAllUserEntries(0);
        assertEquals(1,eventList.size());
        LocalEventOutputData LocalEventOutputData = new LocalEventOutputData(1, "first", 0, LocalDate.parse("2023-11-09"), LocalTime.NOON, LocalTime.parse("20:00"),
                "TA guy's crib", "This is a description.", true, false, new ArrayList<>());
        compareLocalEvents1(LocalEventOutputData);
        verify(LocalEventOutputBoundary,times(1)).UpdateEventsList(refEq(LocalEventOutputData));


        LocalEventInputData LocalEventInputData2 = new LocalEventInputData(0, "second", 0, LocalDate.parse("2022-01-02"), LocalTime.MIDNIGHT, LocalTime.parse("11:00"),
                "TA guy's toilet", "There is no way this is not a description.", true, true, descendent);
        ;
        LocalEventInteractor.CreateEvent(LocalEventInputData2);
        LocalEvent event2 = LocalEventsDataAccessObject.getByID(2);
        compareLocalEvents2(LocalEventInputData2, event2);
        compareLocalEvents(LocalEventInputData2, event2);
        ArrayList<LocalEvent> evenList2 = LocalEventsDataAccessObject.getAllUserEntries(0);
        assertEquals(2, evenList2.size());
        LocalEventOutputData LocalEventOutputData2 = new LocalEventOutputData(2, "second", 0, LocalDate.parse("2022-01-02"), LocalTime.MIDNIGHT, LocalTime.parse("11:00"),
                "TA guy's toilet", "There is no way this is not a description.", true, true, descendent);
        ;
        verify(LocalEventOutputBoundary,times(1)).UpdateEventsList(refEq(LocalEventOutputData2));
    }

    @Test
    public void testEditEvent() throws IOException, ParseException {
        EventsDataAccessObject LocalEventsDataAccessObject = new EventsDataAccessObject("./testEvent"+timeNow+".json");
        LocalEventInteractor LocalEventInteractor =  new LocalEventInteractor(LocalEventsDataAccessObject, LocalEventOutputBoundary);

        LocalEventOutputData LocalEventOutputData = new LocalEventOutputData(1, "first", 0, LocalDate.parse("2023-11-09"), LocalTime.NOON, LocalTime.parse("20:00"),
                "TA guy's crib", "This is a description.", true, false, new ArrayList<>());
        LocalEventInteractor.DisplayEventDetailedView(1);
        verify(LocalEventOutputBoundary,times(1)).DisplayEventDetailedView(refEq(LocalEventOutputData));


        LocalEventInputData LocalEventinputData2 = new LocalEventInputData(1, "firstttt", 0, LocalDate.parse("2023-11-06"), LocalTime.MIDNIGHT, LocalTime.parse("21:00"),
                "TA guy's toilet", "This is a descriptionnnnn.", true, false, new ArrayList<>());
        LocalEventInteractor.EditEvent(LocalEventinputData2);
        LocalEvent event2 = LocalEventsDataAccessObject.getByID(1);
        ArrayList<LocalEvent> eventList2 = LocalEventsDataAccessObject.getAllUserEntries(0);
        assertEquals(2,eventList2.size());
        compareLocalEvents(LocalEventinputData2, event2);
        LocalEventOutputData LocalEventOutputData2 = new LocalEventOutputData(1, "firstttt", 0, LocalDate.parse("2023-11-06"), LocalTime.MIDNIGHT, LocalTime.parse("21:00"),
                "TA guy's toilet", "This is a descriptionnnnn.", true, false, new ArrayList<>());
        verify(LocalEventOutputBoundary,times(1)).UpdateEventsList(refEq(LocalEventOutputData2));

    }

    @Test
    public void testGetAllLocalEvents() throws IOException, ParseException {
        EventsDataAccessObject LocalEventsDataAccessObject = new EventsDataAccessObject("./testEvent"+timeNow+".json");
        LocalEventInteractor LocalEventInteractor =  new LocalEventInteractor(LocalEventsDataAccessObject,LocalEventOutputBoundary);

        LocalEventOutputData LocalEventOutputData1 = new LocalEventOutputData(1, "firstttt", 0, LocalDate.parse("2023-11-06"), LocalTime.MIDNIGHT, LocalTime.parse("21:00"),
                "TA guy's toilet", "This is a descriptionnnnn.", true, false, new ArrayList<>());
        LocalEventOutputData LocalEventOutputData2 = new LocalEventOutputData(2, "second", 0, LocalDate.parse("2022-01-02"), LocalTime.MIDNIGHT, LocalTime.parse("11:00"),
                "TA guy's toilet", "There is no way this is not a description.", true, true, descendent);

        LocalEventInteractor.GetAllEvents(0);
        ArrayList<LocalEventOutputData> LocalEventOutputDataArrayList = new ArrayList<>();
        LocalEventOutputDataArrayList.add(LocalEventOutputData1);
        LocalEventOutputDataArrayList.add(LocalEventOutputData2);

        ArgumentCaptor<ArrayList<LocalEventOutputData>> captor = ArgumentCaptor.forClass(ArrayList.class);
        verify(LocalEventOutputBoundary).DisplayAllEvents(captor.capture());
        ArrayList<LocalEventOutputData> argument = captor.getValue();
        assertEquals(refEq(LocalEventOutputDataArrayList.get(0)),refEq(argument.get(0)));
        assertEquals(refEq(LocalEventOutputDataArrayList.get(1)),refEq(argument.get(1)));
    }

    @Test
    public void testDeleteEvents() throws IOException, ParseException {
        EventsDataAccessObject LocalEventsDataAccessObject = new EventsDataAccessObject("./testEvent"+timeNow+".json");
        LocalEventInteractor LocalEventInteractor =  new LocalEventInteractor(LocalEventsDataAccessObject,LocalEventOutputBoundary);

        LocalEventInteractor.DeleteEvent(1);
        LocalEvent event3 = LocalEventsDataAccessObject.getByID(1);
        ArrayList<LocalEvent> eventList3 = LocalEventsDataAccessObject.getAllUserEntries(0);
        assertNull(event3);
        assertEquals(1,eventList3.size());
        verify(LocalEventOutputBoundary,times(0)).DeleteEventSuccessView(0);
    }


}