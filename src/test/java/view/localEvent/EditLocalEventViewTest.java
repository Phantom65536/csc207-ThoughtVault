package view.localEvent;


import data_access.EventsDataAccessObject;
import interface_adapter.ViewManagerModel;
import interface_adapter.listView.ListViewModel;
import interface_adapter.localEvent.LocalEventController;
import interface_adapter.localEvent.LocalEventPresenter;
import interface_adapter.localEvent.LocalEventViewModel;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.junit.Test;
import use_case.localEvent.LocalEventInteractor;

import javax.swing.*;
import java.io.IOException;

import static java.lang.Thread.sleep;


public class EditLocalEventViewTest {
    @Test
    public void testGetSelectedEvent() throws IOException, ParseException {
        // Read the apiKey environment variable


        ListViewModel listViewModel = new ListViewModel();
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        EventsDataAccessObject entriesDataAccessObject = new EventsDataAccessObject("./testEvent.json");
        LocalEventViewModel LocalEventViewModel = new LocalEventViewModel("edit event view");
        LocalEventViewModel detailedLocalEventViewModel = new LocalEventViewModel("detailed local event view");
        LocalEventViewModel localEventCreationViewModel = new LocalEventViewModel("create event view");
        LocalEventPresenter LocalEventPresenter = new LocalEventPresenter(viewManagerModel,
                detailedLocalEventViewModel,
                localEventCreationViewModel,
                LocalEventViewModel,
                listViewModel);
        LocalEventInteractor inputBoundary = new LocalEventInteractor(entriesDataAccessObject, LocalEventPresenter);
        LocalEventController localEventController = new LocalEventController(inputBoundary);


        JPanel EditLocalEventView = new EditLocalEventView(LocalEventViewModel, viewManagerModel, localEventController);
        JFrame jf = new JFrame();
        jf.setContentPane(EditLocalEventView);
        jf.pack();
        jf.setVisible(true);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jf.setSize(400,400);
        jf.show();

        // So that the app doesn't close automatically.
        try {
            sleep(100000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // Check that the selected event and the exported event are the same
        Assert.assertNotNull(
                LocalEventViewModel.getState().getDescription()
        );

        // Print out what the event titles are
        System.out.println("View model: " + LocalEventViewModel.getState().getDescription());

    }




}