package interface_adapter.importevents;

import interface_adapter.ViewManagerModel;
import interface_adapter.home.HomeViewModel;
import use_case.GCalEventOutputBoundary;
import use_case.GCalEventOutputData;

public class ImportEventsPresenter implements GCalEventOutputBoundary {
    private final ImportEventsViewModel importEventsViewModel;
    private final HomeViewModel homeViewModel;
    private ViewManagerModel viewManagerModel;

    public ImportEventsPresenter(ImportEventsViewModel importEventsViewModel, HomeViewModel homeViewModel, ViewManagerModel viewManagerModel) {
        this.importEventsViewModel = importEventsViewModel;
        this.homeViewModel = homeViewModel;
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
        viewManagerModel.setActiveView(homeViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
