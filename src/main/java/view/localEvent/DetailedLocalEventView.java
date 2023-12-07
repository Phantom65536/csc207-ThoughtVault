package view.localEvent;

import interface_adapter.localEvent.LocalEventViewModel;
import interface_adapter.localEvent.LocalEventController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DetailedLocalEventView extends JPanel{
    private JLabel titleLabel;
    private JLabel locationLabel;
    private JLabel descriptionLabel;
    private JLabel isWorkLabel;
    private JLabel pinnedLabel;
    private JLabel subEventsLabel;
    private JButton deleteButton;
    private JButton editButton;
    private JLabel dateLabel;
    private JLabel startTimeLabel;
    private JLabel endTimeLabel;
    private LocalEventController localEventController;
    public String viewName = "";
    public DetailedLocalEventView (LocalEventViewModel localEventViewModel, LocalEventController localEventController){
        this.viewName = localEventViewModel.getViewName();

        setLayout(new GridLayout(6, 1)); // Adjust the layout as per your preference
        titleLabel = new JLabel(LocalEventViewModel.TITLE_LABEL + localEventViewModel.getState().getTitle());
        locationLabel = new JLabel(LocalEventViewModel.LOCATION_LABEL + localEventViewModel.getState().getLocation());
        descriptionLabel = new JLabel(LocalEventViewModel.DESCRIPTION_LABEL + localEventViewModel.getState().getDescription());
        isWorkLabel = new JLabel(LocalEventViewModel.IS_WORK_LABEL + localEventViewModel.getState().getIsWork());
        pinnedLabel = new JLabel(LocalEventViewModel.PINNED_LABEL + localEventViewModel.getState().getPinned());
        subEventsLabel = new JLabel(LocalEventViewModel.SUB_EVENTS_LABEL + localEventViewModel.getState().getSubNotes().toString());
        dateLabel = new JLabel(LocalEventViewModel.DATE_LABEL + localEventViewModel.getState().getDate().toString());
        startTimeLabel = new JLabel(LocalEventViewModel.START_TIME_LABEL + localEventViewModel.getState().getStartTime().toString());
        endTimeLabel = new JLabel(LocalEventViewModel.END_TIME_LABEL+ localEventViewModel.getState().getEndTime().toString());

        JPanel buttons = new JPanel();

        editButton = new JButton("Edit Event");
        buttons.add(editButton);
        editButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        localEventController.switchToEdit();
                    }
                }
        );

        deleteButton = new JButton("Delete Event");
        buttons.add(deleteButton);
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Action to perform when delete button is clicked
                // For now, let's remove the panel itself
                localEventController.deleteEvent(localEventViewModel.getState().getId());
            }
        });
        add(titleLabel);
        add(locationLabel);
        add(descriptionLabel);
        add(isWorkLabel);
        add(pinnedLabel);
        add(subEventsLabel);
        add(dateLabel);
        add(startTimeLabel);
        add(endTimeLabel);
        add(buttons);

        setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Add a border for clarity
    }
}
