package view.note;
import interface_adapter.ViewManagerModel;
import interface_adapter.note.NoteController;
import interface_adapter.note.NoteViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class CreateNoteView extends JPanel implements ActionListener, PropertyChangeListener {
    public static String viewName = "create note view";
    private final NoteController noteController;
    private JTextField titleField;
    private JTextField locationField;
    private JTextField descriptionField;
    private JCheckBox workCheckBox;
    private JCheckBox pinnedCheckBox;

    private final NoteViewModel noteViewModel;
    private final ViewManagerModel viewManagerModel;

    public CreateNoteView(NoteViewModel noteViewModel, ViewManagerModel viewManagerModel, NoteController noteController) {
        this.noteController = noteController;
        this.noteViewModel = noteViewModel;
        this.viewManagerModel = viewManagerModel;
        this.noteViewModel.addPropertyChangeListener(this);
        this.viewManagerModel.addPropertyChangeListener(this);
        setLayout(new GridLayout(0, 2));

        JLabel titleLabel = new JLabel("Title:");
        titleField = new JTextField();
        JLabel locationLabel = new JLabel("Location:");
        locationField = new JTextField();
        JLabel descriptionLabel = new JLabel("Description:");
        descriptionField = new JTextField();
        JLabel workLabel = new JLabel("Is Work Event:");
        workCheckBox = new JCheckBox();
        JLabel pinnedLabel = new JLabel("Pinned:");
        pinnedCheckBox = new JCheckBox();

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

        JButton createButton = new JButton("Create");
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createEvent();
            }
        });

        add(createButton);
    }
    private void createEvent() {
        String title = titleField.getText();
        String location = locationField.getText();
        String description = descriptionField.getText();
        boolean isWork = workCheckBox.isSelected();
        boolean pinned = pinnedCheckBox.isSelected();

        noteController.createNote(0, title, noteViewModel.getState().getUserId(),
                location, description, isWork,pinned, new ArrayList<Integer>());
        // Here you can create an Event object or perform any action with the collected data
        // For demonstration purposes, printing the collected data
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
