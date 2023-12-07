package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.exportevents.ExportEventsViewModel;
import interface_adapter.importevents.ImportEventsViewModel;
import interface_adapter.listView.ListViewModel;
import interface_adapter.listView.ListViewState;
import interface_adapter.localEvent.LocalEventViewModel;
import interface_adapter.note.NoteViewModel;
import interface_adapter.localEvent.LocalEventController;
import interface_adapter.note.NoteController;
import view.note.CreateNoteView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashMap;

public class ListView extends JPanel implements ActionListener, PropertyChangeListener {
    public final static String viewName = "home page";
    private final ListViewModel listViewModel;
    private final LocalEventViewModel localEventViewModel;
    private final NoteViewModel noteViewModel;
    private final ViewManagerModel viewManagerModel;
    private final ImportEventsViewModel importEventsViewModel;
    private final ExportEventsViewModel exportEventsViewModel;

    private final LocalEventController localEventController;
    private final NoteController noteController;
    //final JList<String> eventsList;
    //final DefaultListModel<String> notesListModel;
    //final JList<String> notesList;
    final JButton importEvents;
    final JButton exportEvents;
    final JButton createEvent;
    final JButton createNote;
    final JPanel eventPanel;
    final JPanel notePanel;
    public ListView(ListViewModel listViewModel,
                    ImportEventsViewModel importEventsViewModel,
                    ExportEventsViewModel exportEventsViewModel,
                    ViewManagerModel viewManagerModel,
                    LocalEventViewModel localEventViewModel,
                    NoteViewModel noteViewModel ,
                    LocalEventController localEventController, NoteController noteController){
        this.localEventViewModel = localEventViewModel;
        this.noteViewModel = noteViewModel;
        this.listViewModel = listViewModel;
        this.importEventsViewModel = importEventsViewModel;
        this.exportEventsViewModel = exportEventsViewModel;
        this.viewManagerModel = viewManagerModel;
        this.localEventController = localEventController;
        this.noteController = noteController;
        this.localEventViewModel.addPropertyChangeListener(this);
        this.noteViewModel.addPropertyChangeListener(this);
        this.listViewModel.addPropertyChangeListener(this);
        this.viewManagerModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(viewName);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        eventPanel = new JPanel();
        // Create buttons for each event
//        for (Object eventId : events.keySet()) {
//            HashMap<String, ?> eventDetails = (HashMap<String, ?>) events.get(eventId);
//
//            JButton button = createEventButton((Integer)eventId, eventDetails);
//            eventPanel.add(button);
//        }
        notePanel = new JPanel();
//        for (Object noteId : notes.keySet()) {
//            HashMap<String, ?> noteDetails = (HashMap<String, ?>) notes.get(noteId);
//
//            JButton button = createEventButton((Integer)noteId, noteDetails);
//            eventPanel.add(button);
//        }
        JPanel buttons = new JPanel();
        importEvents = new JButton(ListViewModel.IMPORT_EVENTS_BUTTON_LABEL);
        buttons.add(importEvents);
        importEvents.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(importEvents)){
                            viewManagerModel.setActiveView(importEventsViewModel.getViewName());
                            viewManagerModel.firePropertyChanged();
                        }
                    }
                }
        );
        createEvent = new JButton("Create Event");
        buttons.add(createEvent);
        createEvent.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(createEvent)){
                            localEventViewModel.setUserId(listViewModel.getState().getUserId());
                            viewManagerModel.setActiveView(CreateNoteView.viewName);
                            viewManagerModel.firePropertyChanged();
                        }
                    }
                }
        );
        createNote = new JButton("Create Note");
        buttons.add(createNote);
        createEvent.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(createNote)){
                            noteViewModel.setUserId(listViewModel.getState().getUserId());
                            viewManagerModel.setActiveView(CreateNoteView.viewName);
                            viewManagerModel.firePropertyChanged();
                        }
                    }
                }
        );
        exportEvents = new JButton(ListViewModel.EXPORT_EVENTS_BUTTON_LABEL);
        buttons.add(exportEvents);
        exportEvents.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(exportEvents)){
                            viewManagerModel.setActiveView(exportEventsViewModel.getViewName());
                            viewManagerModel.firePropertyChanged();
                        }
                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(buttons);
        this.add(eventPanel);
        this.add(notePanel);
    }


    // Method to create an event button with ActionListener
    private JButton createEventButton(Integer eventId, HashMap<String, ?> eventDetails) {
        JButton button = new JButton("Event: " + eventDetails.get("title"));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ListView.this.localEventController.displayEventDetailedView(eventId);
            }
        });
        return button;
    }

    private JButton createNoteButton(Integer noteId, HashMap<String, ?> noteDetails) {
        JButton button = new JButton("Note: " + noteDetails.get("title"));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ListView.this.noteController.displayNoteDetailedView(noteId);
            }
        });
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("view")) {
            ListViewState listViewState = listViewModel.getState();
            for (int allID : listViewState.getNotes().keySet()) {
                JButton newBut = createNoteButton(allID, listViewState.getNotes().get(allID));
                notePanel.add(newBut);
            }
            for (int allID : listViewState.getEvents().keySet()) {
                JButton newBut = createEventButton(allID, listViewState.getEvents().get(allID));
                eventPanel.add(newBut);
            }
        }
    }
    // Create a sample event map with details
}
