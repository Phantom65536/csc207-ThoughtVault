package view.localEvent;

import interface_adapter.ViewManagerModel;
import interface_adapter.localEvent.LocalEventController;
import interface_adapter.localEvent.LocalEventState;
import interface_adapter.localEvent.LocalEventViewModel;
import interface_adapter.note.NoteState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;

public class CreateLocalEventView extends JPanel implements ActionListener, PropertyChangeListener {
    public static String viewName = "create event view";
    private JTextField titleField;
    private JTextField dateField;
    private JTextField startTimeField;
    private JTextField endTimeField;
    private JTextField locationField;
    private JTextField descriptionField;
    private JCheckBox workCheckBox;
    private JCheckBox pinnedCheckBox;
    private LocalEventViewModel localEventViewModel;
    private ViewManagerModel viewManagerModel;
    private LocalEventController localEventController;
    final DefaultListModel<String> eventsListModel;
    private JList<String> eventsList;

    public CreateLocalEventView(LocalEventViewModel localEventViewModel, ViewManagerModel viewManagerModel, LocalEventController localEventController) {
        this.localEventController = localEventController;
        this.localEventViewModel = localEventViewModel;
        this.viewManagerModel = viewManagerModel;
        setLayout(new GridLayout(0, 2));

        JLabel titleLabel = new JLabel("Title:");
        titleField = new JTextField();
        JLabel dateLabel = new JLabel("Date (YYYY-MM-DD):");
        dateField = new JTextField();
        JLabel startTimeLabel = new JLabel("Start Time (HH:MM):");
        startTimeField = new JTextField();
        JLabel endTimeLabel = new JLabel("End Time (HH:MM):");
        endTimeField = new JTextField();
        JLabel locationLabel = new JLabel("Location:");
        locationField = new JTextField();
        JLabel descriptionLabel = new JLabel("Description:");
        descriptionField = new JTextField();
        JLabel workLabel = new JLabel("Is Work Event: (Unchecked means PERSONAL)");
        workCheckBox = new JCheckBox();
        JLabel pinnedLabel = new JLabel("Pinned:");
        pinnedCheckBox = new JCheckBox();

        JLabel subEventLabel = new JLabel(LocalEventViewModel.SUB_EVENTS_LABEL );
        eventsListModel = new DefaultListModel<>();
        eventsList = new JList<>(eventsListModel);
        eventsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JButton addSubEventButton = new JButton("Add the selected sub-event");
        JLabel subEventsTOBeAdded = new JLabel();

        addSubEventButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int setIndex = eventsList.getSelectedIndex();
                String selectionTitle = eventsList.getModel().getElementAt(setIndex);
                int iend = selectionTitle.indexOf(":");
                int entryId = Integer.parseInt(selectionTitle.substring(0,iend));
                LocalEventState eventState = localEventViewModel.getState();
                eventState.addEntryId(entryId);
                subEventsTOBeAdded.setText(subEventsTOBeAdded.getText()+eventState.getAllEntries().get(entryId));
            }
        });
        
        add(titleLabel);
        add(titleField);
        add(dateLabel);
        add(dateField);
        add(startTimeLabel);
        add(startTimeField);
        add(endTimeLabel);
        add(endTimeField);
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
        LocalDate date = LocalDate.parse(dateField.getText());
        LocalTime startTime = LocalTime.parse(startTimeField.getText());
        LocalTime endTime = LocalTime.parse(endTimeField.getText());
        String location = locationField.getText();
        String description = descriptionField.getText();
        boolean isWork = workCheckBox.isSelected();
        boolean pinned = pinnedCheckBox.isSelected();

        localEventController.createEvent(0, title, localEventViewModel.getState().getUserId(),
                date, startTime, endTime, location, description, isWork,pinned, new ArrayList<Integer>());

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("view")) {
            LocalEventState eventState = localEventViewModel.getState();
            HashMap<Integer, String> subEventsMap = eventState.getAllEntries();
            for (int id : subEventsMap.keySet()) {
                eventsListModel.addElement(subEventsMap.get(id));
            }
        }
    }
}
