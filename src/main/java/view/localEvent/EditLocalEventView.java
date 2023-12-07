package view.localEvent;

import interface_adapter.ViewManagerModel;
import interface_adapter.localEvent.LocalEventState;
import interface_adapter.localEvent.LocalEventViewModel;
import interface_adapter.localEvent.LocalEventController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

public class EditLocalEventView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "edit event";
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

    private JList<String> eventsList;

    final DefaultListModel<String> eventsListModel;

    private LocalEventController localEventController;

    public EditLocalEventView(LocalEventViewModel localEventViewModel, ViewManagerModel viewManagerModel, LocalEventController localEventController) {
        this.localEventController = localEventController;
        this.localEventViewModel = localEventViewModel;
        this.viewManagerModel = viewManagerModel;
        this.localEventViewModel.addPropertyChangeListener(this);
        this.viewManagerModel.addPropertyChangeListener(this);

        setLayout(new GridLayout(0, 2));

        JLabel titleLabel = new JLabel(LocalEventViewModel.TITLE_LABEL);
        titleField = new JTextField();
        JLabel dateLabel = new JLabel(LocalEventViewModel.DATE_LABEL);
        dateField = new JTextField();
        JLabel startTimeLabel = new JLabel(LocalEventViewModel.START_TIME_LABEL);
        startTimeField = new JTextField();
        JLabel endTimeLabel = new JLabel(LocalEventViewModel.END_TIME_LABEL);
        endTimeField = new JTextField();
        JLabel locationLabel = new JLabel(LocalEventViewModel.LOCATION_LABEL);
        locationField = new JTextField();
        JLabel descriptionLabel = new JLabel(LocalEventViewModel.DESCRIPTION_LABEL);
        descriptionField = new JTextField();
        JLabel workLabel = new JLabel(LocalEventViewModel.IS_WORK_LABEL);
        workCheckBox = new JCheckBox();
        workCheckBox.setSelected(false);
        JLabel pinnedLabel = new JLabel(LocalEventViewModel.PINNED_LABEL );
        pinnedCheckBox = new JCheckBox();
        pinnedCheckBox.setSelected(false);

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

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editEvent();
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
        add(saveButton);
        add(eventsList);
        add(subEventLabel);
        add(addSubEventButton);
    }

    private void editEvent() {
        String title = titleField.getText();
        LocalDate date = LocalDate.parse(dateField.getText());
        LocalTime startTime = LocalTime.parse(startTimeField.getText());
        LocalTime endTime = LocalTime.parse(endTimeField.getText());
        String location = locationField.getText();
        String description = descriptionField.getText();
        boolean isWork = workCheckBox.isSelected();
        boolean pinned = pinnedCheckBox.isSelected();

        // Call the editEvent method in your localEventController passing the updated information
        localEventController.editEvent(localEventViewModel.getState().getId(),
                title,localEventViewModel.getState().getUserId(),date,startTime,
                endTime,location,description,isWork,pinned,new ArrayList<Integer>());
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("view")) {
            titleField.setText(localEventViewModel.getState().getTitle());
            dateField.setText(localEventViewModel.getState().getDate().format(DateTimeFormatter.ISO_LOCAL_DATE));
            startTimeField.setText(localEventViewModel.getState().getStartTime().format(DateTimeFormatter.ISO_LOCAL_DATE));
            endTimeField.setText(localEventViewModel.getState().getEndTime().format(DateTimeFormatter.ISO_LOCAL_DATE));
            locationField.setText(localEventViewModel.getState().getLocation());
            descriptionField.setText(localEventViewModel.getState().getDescription());
            workCheckBox.setSelected(localEventViewModel.getState().getIsWork());
            pinnedCheckBox.setSelected(localEventViewModel.getState().getPinned());

            ArrayList<Integer> subEventId = localEventViewModel.getState().getSubEntries();
            HashMap<Integer,String> subEventMap = localEventViewModel.getState().getAllEntries();
            for (int id: subEventId){
                eventsListModel.addElement(subEventMap.get(id));
            }
        }
    }
}