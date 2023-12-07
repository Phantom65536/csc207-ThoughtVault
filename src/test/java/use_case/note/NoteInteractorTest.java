package use_case.note;

import data_access.NotesDataAccessObject;
import entity.note.Note;
import junit.framework.TestCase;
import org.json.simple.parser.ParseException;
import org.junit.*;
import org.mockito.Answers;
import org.mockito.ArgumentCaptor;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class NoteInteractorTest extends TestCase {

    public static  String timeNow = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss.SSS").format(new Date());
    public static  NoteOutputBoundary noteOutputBoundary = mock(NoteOutputBoundary.class, Answers.CALLS_REAL_METHODS);

    public static ArrayList<Integer> descendent = new ArrayList<>(Arrays.asList(1, 1000));

    public void compareNotes(NoteInputData noteInputData, Note event){
        assertEquals(noteInputData.getTitle(), event.getTitle());
        assertEquals(noteInputData.getUserID(), event.getUserID());
        assertEquals(noteInputData.getLocation(), event.getLocation());
        assertEquals(noteInputData.getDescription(), event.getDescription());
        assertEquals(noteInputData.getIsWork(), event.isWork());
        assertEquals(noteInputData.getPinned(), event.getPinned());
        assertEquals(noteInputData.getSubEvents(), event.getDescendants());
    }
    public void compareNotes1(NoteInputData noteInputData, Note event){
        assertEquals(event.getID(), 1);
        assertEquals(noteInputData.getTitle(), "first");
        assertEquals(noteInputData.getUserID(), 0);
        assertEquals(noteInputData.getLocation(), "TA guy's crib");
        assertEquals(noteInputData.getDescription(), "This is a description.");
        assertEquals(noteInputData.getIsWork(), true);
        assertEquals(noteInputData.getPinned(), false);
        assertEquals(noteInputData.getSubEvents().size(), 0);

        assertEquals(event.getID(), 1);
        assertEquals(event.getTitle(), "first");
        assertEquals(event.getUserID(), 0);
        assertEquals(event.getLocation(), "TA guy's crib");
        assertEquals(event.getDescription(), "This is a description.");
        assertEquals(event.isWork(), true);
        assertEquals(event.getPinned(), false);
        assertEquals(event.getDescendants().size(), 0);
    }

    public void compareNotes2(NoteInputData noteInputData, Note event){

        assertEquals(event.getID(), 2);
        assertEquals(noteInputData.getTitle(), "second");
        assertEquals(noteInputData.getUserID(), 0);
        assertEquals(noteInputData.getLocation(), "TA guy's cribbbbb");
        assertEquals(noteInputData.getDescription(), "This is a descriptionssss.");
        assertEquals(noteInputData.getIsWork(), false);
        assertEquals(noteInputData.getPinned(), false);
        assertEquals(noteInputData.getSubEvents().size(), 2);

        assertEquals(event.getID(), 2);
        assertEquals(event.getTitle(), "second");
        assertEquals(event.getUserID(), 0);
        assertEquals(event.getLocation(), "TA guy's cribbbbb");
        assertEquals(event.getDescription(), "This is a descriptionssss.");
        assertEquals(event.isWork(), false);
        assertEquals(event.getPinned(), false);
        assertEquals(event.getDescendants().size(), 2);
    }


    @Test
    public void testCreateNote() throws IOException, ParseException {

        NotesDataAccessObject notesDataAccessObject = new NotesDataAccessObject("./testNotes"+timeNow+".json");
        NoteInteractor noteInteractor =  new NoteInteractor(noteOutputBoundary, notesDataAccessObject);


        NoteInputData noteInputData = new NoteInputData(0, "first", 0,
                "TA guy's crib", "This is a description.", true, false, new ArrayList<>());
        noteInteractor.CreateNote(noteInputData);
        Note event = notesDataAccessObject.getByID(1);
        compareNotes1(noteInputData, event);
        compareNotes(noteInputData, event);
        ArrayList<Note> eventList = notesDataAccessObject.getAllUserEntries(0);
        assertEquals(1,eventList.size());
        NoteOutputData noteOutputData = new NoteOutputData(1, "first", 0,
                "TA guy's crib", "This is a description.", true, false, new ArrayList<>());
        verify(noteOutputBoundary,times(1)).UpdateNotesList(refEq(noteOutputData));


        NoteInputData noteInputData2 = new NoteInputData(0, "second", 0,
                "TA guy's cribbbbb", "This is a descriptionssss.", false, false, descendent);
        noteInteractor.CreateNote(noteInputData2);
        Note event2 = notesDataAccessObject.getByID(2);
        compareNotes2(noteInputData2, event2);
        compareNotes(noteInputData2, event2);
        ArrayList<Note> evenList2 = notesDataAccessObject.getAllUserEntries(0);
        assertEquals(2, evenList2.size());
        NoteOutputData noteOutputData2 = new NoteOutputData(2, "second", 0,
                "TA guy's cribbbbb", "This is a descriptionssss.", false, false, descendent);
        verify(noteOutputBoundary,times(1)).UpdateNotesList(refEq(noteOutputData2));
    }

    public void testEditNote() throws IOException, ParseException {
        NotesDataAccessObject notesDataAccessObject = new NotesDataAccessObject("./testNotes"+timeNow+".json");
        NoteInteractor noteInteractor =  new NoteInteractor(noteOutputBoundary, notesDataAccessObject);

        NoteOutputData noteOutputData = new NoteOutputData(1, "first", 0,
                "TA guy's crib", "This is a description.", true, false, new ArrayList<>());

        noteInteractor.DisplayNoteDetailedView(1);
        verify(noteOutputBoundary,times(1)).DisplayNoteDetailedView(refEq(noteOutputData));


        NoteInputData noteinputData2 = new NoteInputData(1, "firstttt", 0,
                "TA guy's toilet", "This is not a description.", true, true, new ArrayList<>());
        noteInteractor.EditNote(noteinputData2);
        Note event2 = notesDataAccessObject.getByID(1);
        ArrayList<Note> eventList2 = notesDataAccessObject.getAllUserEntries(0);
        assertEquals(2,eventList2.size());
        compareNotes(noteinputData2, event2);
        NoteOutputData noteOutputData2 = new NoteOutputData(1, "firstttt", 0,
                "TA guy's toilet", "This is not a description.", true, true, new ArrayList<>());
        verify(noteOutputBoundary,times(1)).UpdateNotesList(refEq(noteOutputData2));

    }

    public void testGetAllNotes() throws IOException, ParseException {
        NotesDataAccessObject notesDataAccessObject = new NotesDataAccessObject("./testNotes"+timeNow+".json");
        NoteInteractor noteInteractor =  new NoteInteractor(noteOutputBoundary, notesDataAccessObject);

        NoteOutputData noteOutputData1 = new NoteOutputData(1, "firstttt", 0,
                "TA guy's toilet", "This is not a description.", true, true, new ArrayList<>());
        NoteOutputData noteOutputData2 = new NoteOutputData(2, "second", 0,
                "TA guy's cribbbbb", "This is a descriptionssss.", false, false, descendent);

        noteInteractor.GetAllNotes(0);
        ArrayList<NoteOutputData> noteOutputDataArrayList = new ArrayList<>();
        noteOutputDataArrayList.add(noteOutputData1);
        noteOutputDataArrayList.add(noteOutputData2);

        ArgumentCaptor<ArrayList<NoteOutputData>> captor = ArgumentCaptor.forClass(ArrayList.class);
        verify(noteOutputBoundary).DisplayAllNotes(captor.capture());
        ArrayList<NoteOutputData> argument = captor.getValue();
        assertEquals(refEq(noteOutputDataArrayList.get(0)),refEq(argument.get(0)));
        assertEquals(refEq(noteOutputDataArrayList.get(1)),refEq(argument.get(1)));
    }

    public void testDeleteNode() throws IOException, ParseException {
        NotesDataAccessObject notesDataAccessObject = new NotesDataAccessObject("./testNotes"+timeNow+".json");
        NoteInteractor noteInteractor =  new NoteInteractor(noteOutputBoundary, notesDataAccessObject);

        noteInteractor.DeleteNote(1);
        Note event3 = notesDataAccessObject.getByID(1);
        ArrayList<Note> eventList3 = notesDataAccessObject.getAllUserEntries(0);
        assertNull(event3);
        assertEquals(1,eventList3.size());
        verify(noteOutputBoundary,times(0)).DeleteNoteSuccessView(0);
    }


}