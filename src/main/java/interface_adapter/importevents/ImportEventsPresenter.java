package interface_adapter.importevents;

import interface_adapter.ViewManagerModel;
import use_case.gcalevent.GCalEventOutputBoundary;
import use_case.gcalevent.GCalEventOutputData;

public class ImportEventsPresenter implements GCalEventOutputBoundary {
    private final ImportEventsViewModel importEventsViewModel;
    private ViewManagerModel viewManagerModel;

    public ImportEventsPresenter(ImportEventsViewModel importEventsViewModel, ViewManagerModel viewManagerModel) {
        this.importEventsViewModel = importEventsViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(GCalEventOutputData response) {
        ImportEventsState importEventsState = importEventsViewModel.getState();
        importEventsState.setImportedEventSummary(response.getTitle());
        importEventsViewModel.setState(importEventsState);
        importEventsViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(importEventsViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error){
        ImportEventsState importEventsState = importEventsViewModel.getState();
        importEventsState.setImportEventError(error);
        importEventsViewModel.firePropertyChanged();
    }
}
