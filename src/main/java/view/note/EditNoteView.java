package view.note;

import interface_adapter.ViewManagerModel;
import interface_adapter.importevents.ImportEventsState;
import interface_adapter.note.NoteState;
import interface_adapter.note.NoteViewModel;
import interface_adapter.note.NoteController;
import use_case.gcalevent.GCalEventInputData;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashMap;

public class EditNoteView extends JPanel implements ActionListener, PropertyChangeListener {
    public static String viewName = "edit event view";
    private JTextField titleField;
    private JTextField locationField;
    private JTextField descriptionField;
    private JCheckBox workCheckBox;
    private JCheckBox pinnedCheckBox;

    private NoteViewModel noteViewModel;
    private ViewManagerModel viewManagerModel;
    final DefaultListModel<String> notesListModel;
    final JList<String> notesList;

    private NoteController noteController;
    public EditNoteView (NoteViewModel noteViewModel, ViewManagerModel viewManagerModel, NoteController noteController){
        this.noteController = noteController;
        this.noteViewModel = noteViewModel;
        this.viewManagerModel = viewManagerModel;
        this.noteViewModel.addPropertyChangeListener(this);
        this.viewManagerModel.addPropertyChangeListener(this);
        setLayout(new GridLayout(0, 2));

        JLabel titleLabel = new JLabel(noteViewModel.TITLE_LABEL);
        titleField = new JTextField();
        JLabel locationLabel = new JLabel(NoteViewModel.LOCATION_LABEL);
        locationField = new JTextField();
        JLabel descriptionLabel = new JLabel(noteViewModel.DESCRIPTION_LABEL);
        descriptionField = new JTextField();
        JLabel workLabel = new JLabel(noteViewModel.IS_WORK_LABEL);
        workCheckBox = new JCheckBox();
        workCheckBox.setSelected(false);
        JLabel pinnedLabel = new JLabel(noteViewModel.PINNED_LABEL );
        pinnedCheckBox = new JCheckBox();
        pinnedCheckBox.setSelected(false);

        JLabel subNotesLabel = new JLabel(NoteViewModel.SUB_NOTES_LABEL);
        notesListModel = new DefaultListModel<>();
        notesList = new JList<>(notesListModel);
        notesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JButton addSubNote = new JButton("Add the selected sub-note");
        JLabel subNotesToBeAdded = new JLabel();

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editNote();
            }
        });

        addSubNote.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selIndex = notesList.getSelectedIndex();
                String selectionTitle = notesList.getModel().getElementAt(selIndex);
                int iend = selectionTitle.indexOf(":");
                int noteID = Integer.parseInt(selectionTitle.substring(0, iend));
                NoteState noteState = noteViewModel.getState();
                noteState.addEntryId(noteID);
                subNotesToBeAdded.setText(subNotesToBeAdded.getText() + noteState.getAllEntries().get(noteID));
            }
        });

        add(titleLabel);
        add(titleField);
        add(locationLabel);
        add(locationField);
        add(descriptionLabel);
        add(descriptionField);
        add(workLabel);
        add(workCheckBox);
        add(pinnedLabel);
        add(pinnedCheckBox);
        add(subNotesLabel);
        add(notesList);
        add(addSubNote);
        add(saveButton);
    }
    private void editNote() {
        String title = titleField.getText();
        String location = locationField.getText();
        String description = descriptionField.getText();
        boolean isWork = workCheckBox.isSelected();
        boolean pinned = pinnedCheckBox.isSelected();

        // Call the editEvent method in your localEventController passing the updated information
        noteController.editNote(noteViewModel.getState().getId(),
                title,noteViewModel.getState().getUserId(),
                location,description,isWork,pinned,new ArrayList<Integer>());
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("view")) {
            NoteState noteState = noteViewModel.getState();
            titleField.setText(noteState.getTitle());
            descriptionField.setText(noteState.getDescription());
            locationField.setText(noteState.getLocation());
            workCheckBox.setSelected(noteState.getIsWork());
            pinnedCheckBox.setSelected(noteState.getPinned());

            ArrayList<Integer> subNotesId = noteState.getSubEntries();
            HashMap<Integer, String> subNotesMap = noteState.getAllEntries();
            for (int id : subNotesId) {
                notesListModel.addElement(subNotesMap.get(id));
            }
        }
    }
}

