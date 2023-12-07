package interface_adapter.importevents;

import interface_adapter.ViewManagerModel;
import interface_adapter.listView.ListViewModel;
import use_case.gcalevent.GCalEventOutputBoundary;
import use_case.gcalevent.GCalEventOutputData;

public class ImportEventsPresenter implements GCalEventOutputBoundary {
    private final ImportEventsViewModel importEventsViewModel;
    private final ListViewModel listViewModel;
    private ViewManagerModel viewManagerModel;

    public ImportEventsPresenter(ImportEventsViewModel importEventsViewModel, ListViewModel listViewModel, ViewManagerModel viewManagerModel) {
        this.importEventsViewModel = importEventsViewModel;
        this.listViewModel = listViewModel;
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

    @Override
    public void switchToHome() {
        importEventsViewModel.setState(new ImportEventsState());
        importEventsViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(listViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
