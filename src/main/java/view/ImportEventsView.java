package view;

import interface_adapter.home.HomeViewModel;
import interface_adapter.importevents.ImportEventsController;
import interface_adapter.importevents.ImportEventsState;
import interface_adapter.importevents.ImportEventsViewModel;
import use_case.GCalEventInputData;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;

public class ImportEventsView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "import events";
    private final ImportEventsViewModel importEventsViewModel;
    final JTextField eventsField = new JTextField(15);
    final DefaultListModel<String> eventsListModel;
    final JList<String> eventsList;
    final JButton importEvents;
    final JButton home;
    private final ImportEventsController importEventsController;

    public ImportEventsView(ImportEventsViewModel importEventsViewModel, ImportEventsController controller) throws IOException {

        this.importEventsController = controller;
        this.importEventsViewModel = importEventsViewModel;
        this.importEventsViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel("Import Events Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel eventsInfo = new LabelTextPanel(
                new JLabel("Events"), eventsField);

        // Create a list to display events
        eventsListModel = new DefaultListModel<>();
        ImportEventsState currentState = importEventsViewModel.getState();
        currentState.setListOfEvents(controller.getAllEvents());
        for (GCalEventInputData event : currentState.getListOfEvents()) {
            eventsListModel.addElement(event.getTitle());
        }

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

        JPanel buttons = new JPanel();
        importEvents = new JButton(ImportEventsViewModel.IMPORT_EVENTS_BUTTON_LABEL);
        buttons.add(importEvents);

        importEvents.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(importEvents)) {
                            ImportEventsState currentState = importEventsViewModel.getState();
                            if (currentState.getSelectedEventIndex() != -1) {
                                try {
                                    importEventsController.execute(currentState.getSelectedEventId());
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                            importEventsViewModel.setState(currentState);
                        }
                    }
                }
        );

        home = new JButton(HomeViewModel.HOME_BUTTON_LABEL);
        buttons.add(home);

        home.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(home)){
                            importEventsController.switchToHome();
                        }
                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(eventsList);
        this.add(buttons);
    }
        public void actionPerformed(ActionEvent evt){
            JOptionPane.showConfirmDialog(this, "You are importing an event");
        }

        @Override
        public void propertyChange(PropertyChangeEvent evt){
            ImportEventsState state = (ImportEventsState) evt.getNewValue();
            if (state.getImportEventError() != null){
                JOptionPane.showMessageDialog(this, state.getImportEventError());
            } else if (state.getImportedEventSummary() != null){
                JOptionPane.showMessageDialog(this, "You have imported:" + state.getImportedEventSummary());
            }
        }
    }
