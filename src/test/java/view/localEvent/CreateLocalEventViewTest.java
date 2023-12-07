package view.localEvent;

import data_access.EventsDataAccessObject;
import interface_adapter.ViewManagerModel;
import interface_adapter.listView.ListViewModel;
import interface_adapter.localEvent.LocalEventController;
import interface_adapter.localEvent.LocalEventPresenter;
import interface_adapter.localEvent.LocalEventViewModel;
import org.json.simple.parser.ParseException;
import org.junit.Test;
import use_case.localEvent.LocalEventInteractor;

import javax.swing.*;
import java.io.IOException;

import static java.lang.Thread.sleep;
import static org.junit.Assert.*;

public class CreateLocalEventViewTest {
    @Test
    public void testCreateLocalEventViewTest() throws IOException, ParseException {
        ListViewModel listViewModel = new ListViewModel();
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        EventsDataAccessObject entriesDataAccessObject = new EventsDataAccessObject("./testEvent.json");
        LocalEventViewModel LocalEventViewModel = new LocalEventViewModel("edit event view");
        LocalEventViewModel detailedLocalEventViewModel = new LocalEventViewModel("detailed local event view");
        LocalEventViewModel localEventCreationViewModel = new LocalEventViewModel("create event view");
        interface_adapter.localEvent.LocalEventPresenter LocalEventPresenter = new LocalEventPresenter(viewManagerModel,
                detailedLocalEventViewModel,
                localEventCreationViewModel,
                LocalEventViewModel,
                listViewModel);
        LocalEventInteractor inputBoundary = new LocalEventInteractor(entriesDataAccessObject, LocalEventPresenter);
        LocalEventController localEventController = new LocalEventController(inputBoundary);

        JPanel CreateLocalEventView = new CreateLocalEventView(LocalEventViewModel, localEventController);
        JFrame jf = new JFrame();
        jf.setContentPane(CreateLocalEventView);
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
    }

}