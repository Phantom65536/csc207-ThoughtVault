package view;

import interface_adapter.exportevents.ExportEventsController;
import interface_adapter.exportevents.ExportEventsState;
import interface_adapter.exportevents.ExportEventsViewModel;
import interface_adapter.home.HomeViewModel;
import output_data.LocalEventOutputData;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;

public class ExportEventsView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "export events";
    private final ExportEventsViewModel exportEventsViewModel;
    final JTextField eventsField = new JTextField(15);
    final DefaultListModel<String> eventsListModel;
    final JList<String> eventsList;
    final JButton exportEvents;
    private final ExportEventsController exportEventsController;
    final JButton home;

    public ExportEventsView(ExportEventsViewModel exportEventsViewModel, ExportEventsController exportEventsController) throws IOException {
        this.exportEventsViewModel = exportEventsViewModel;
        this.exportEventsController = exportEventsController;
        this.exportEventsViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel("Export Events Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel eventsInfo = new LabelTextPanel(
                new JLabel("Events"), eventsField
        );

        // Create a list to display events
        eventsListModel = new DefaultListModel<>();
        ExportEventsState currentState = exportEventsViewModel.getState();
        currentState.setListOfLocalEvents(exportEventsController.getAllLocalEvents());
        for (LocalEventOutputData event : currentState.getListOfLocalEvents()) {
            eventsListModel.addElement(event.getTitle());
        }

        // Allows the user to select the event that they want to import
        eventsList = new JList<>(eventsListModel);
        eventsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        eventsList.addListSelectionListener(
                new ListSelectionListener() {
                    @Override
                    public void valueChanged(ListSelectionEvent e) {
                        int selectedIndex = eventsList.getSelectedIndex();
                        currentState.setSelectedEventIndex(selectedIndex);
                        currentState.setSelectedEvent(selectedIndex);
                    }
                }
        );

        // Exports the selected event upon clicking the Export Events button
        JPanel buttons = new JPanel();
        exportEvents = new JButton(ExportEventsViewModel.EXPORT_EVENTS_BUTTON_LABEL);
        buttons.add(exportEvents);

        exportEvents.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(exportEvents)) {
                            ExportEventsState currentState = exportEventsViewModel.getState();
                            if (currentState.getSelectedEventIndex() != -1) {
                                try {
                                    // exportEventsController.execute(currentState.getEntryID());
                                    if (currentState.getSelectedEvent() != null){
                                        exportEventsController.execute(currentState.getSelectedEventId());
                                    } else {
                                        currentState.setExportEventError("No event selected");
                                        exportEventsViewModel.firePropertyChanged();
                                    }
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                            exportEventsViewModel.setState(currentState);
                        }
                    }
                }
        );

        // Switches to Home view upon clicking Home button
        home = new JButton(HomeViewModel.HOME_BUTTON_LABEL);
        buttons.add(home);

        home.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(home)){
                            exportEventsController.switchToHome();
                        }
                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(eventsList);
        this.add(buttons);
    }

    public void actionPerformed(ActionEvent evt) {
        JOptionPane.showConfirmDialog(this, "You are exporting an event");
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        ExportEventsState state = (ExportEventsState) evt.getNewValue();
        if (state.getExportEventError() != null) {
            JOptionPane.showMessageDialog(this, state.getExportEventError());
        } else if (state.getExportedEventSummary() != null){
            JOptionPane.showMessageDialog(this, "You have exported:" + state.getExportedEventSummary());
        }
    }
}
