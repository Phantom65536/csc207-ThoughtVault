package view.note;

import interface_adapter.ViewManagerModel;
import interface_adapter.note.NoteState;
import interface_adapter.note.NoteViewModel;
import interface_adapter.note.NoteController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashMap;

public class DetailedNoteView extends JPanel implements ActionListener, PropertyChangeListener {
    public static final String viewName = "detailed note view";
    private JLabel titleLabel;
    private JLabel titleContent;
    private JLabel locationLabel;
    private JLabel locationContent;
    private JLabel descriptionLabel;
    private JLabel descriptionContent;
    private JLabel isWorkLabel;
    private JLabel isWorkContent;
    private JLabel pinnedLabel;
    private JLabel pinnedContent;
    private JLabel subNotesLabel;
    private JLabel subNotesContent;
    private JButton deleteButton;
    private JButton editButton;
    private final NoteController noteController;
    private final NoteViewModel noteViewModel;
    private final ViewManagerModel viewManagerModel;
    public DetailedNoteView (NoteViewModel noteViewModel, ViewManagerModel viewManagerModel, NoteController noteController){
        this.noteController = noteController;
        this.noteViewModel = noteViewModel;
        this.viewManagerModel = viewManagerModel;
        this.noteViewModel.addPropertyChangeListener(this);
        this.viewManagerModel.addPropertyChangeListener(this);

        setLayout(new GridLayout(6, 1)); // Adjust the layout as per your preference
        titleLabel = new JLabel(NoteViewModel.TITLE_LABEL);
        titleContent = new JLabel();
        locationLabel = new JLabel(NoteViewModel.LOCATION_LABEL);
        locationContent = new JLabel();
        descriptionLabel = new JLabel(NoteViewModel.DESCRIPTION_LABEL);
        descriptionContent = new JLabel();
        isWorkLabel = new JLabel(NoteViewModel.IS_WORK_LABEL);
        isWorkContent = new JLabel();
        pinnedLabel = new JLabel(NoteViewModel.PINNED_LABEL);
        pinnedContent = new JLabel();
        subNotesLabel = new JLabel(NoteViewModel.SUB_NOTES_LABEL);
        subNotesContent = new JLabel();

        editButton = new JButton("Edit Note");
        editButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        noteController.switchToEdit();
                    }
                }
        );

        deleteButton = new JButton("Delete Note");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Action to perform when delete button is clicked
                // For now, let's remove the panel itself
                noteController.deleteNote(noteViewModel.getState().getId());
            }
        });

        add(titleLabel);
        add(titleContent);
        add(locationLabel);
        add(locationContent);
        add(descriptionLabel);
        add(descriptionContent);
        add(isWorkLabel);
        add(isWorkContent);
        add(pinnedLabel);
        add(pinnedContent);
        add(subNotesLabel);
        add(subNotesContent);
        add(editButton);
        add(deleteButton);

        setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Add a border for clarity
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("view")) {
            NoteState noteState = noteViewModel.getState();
            titleContent.setText(noteState.getTitle());
            descriptionContent.setText(noteState.getDescription());
            locationContent.setText(noteState.getLocation());
            isWorkContent.setText(noteState.getIsWork() ? "WORK" : "PERSONAL");
            pinnedContent.setText(noteState.getPinned() ? "Yes" : "No");

            ArrayList<Integer> subNotesId = noteState.getSubEntries();
            HashMap<Integer, String> subNotesMap = noteState.getAllEntries();
            String subNotesText = "";
            for (int id : subNotesId) {
                subNotesText += subNotesMap.get(id) + "\n";
            }
            subNotesContent.setText(subNotesText);
        }
    }
}
