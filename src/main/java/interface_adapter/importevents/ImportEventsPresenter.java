package interface_adapter.importevents;

import interface_adapter.ViewManagerModel;
import interface_adapter.listView.ListViewModel;
import use_case.gcalevent.GCalEventOutputBoundary;
import use_case.gcalevent.GCalEventOutputData;

/**
 * Presenter class for importing events
 */
public class ImportEventsPresenter implements GCalEventOutputBoundary {
    private final ImportEventsViewModel importEventsViewModel;
    private final ListViewModel listViewModel;
    private ViewManagerModel viewManagerModel;

    /**
     * Instantiate a new ImportEventsPresenter which uses the following view models.
     * @param importEventsViewModel
     * @param listViewModel
     * @param viewManagerModel
     */
    public ImportEventsPresenter(ImportEventsViewModel importEventsViewModel, ListViewModel listViewModel, ViewManagerModel viewManagerModel) {
        this.importEventsViewModel = importEventsViewModel;
        this.listViewModel = listViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    /**
     * Reset import event state if importing event is successful
     * @param response
     */
    @Override
    public void prepareSuccessView(GCalEventOutputData response) {
        ImportEventsState importEventsState = importEventsViewModel.getState();
        importEventsState.setImportedEventSummary(response.getTitle());
        importEventsViewModel.setState(importEventsState);
        importEventsViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(importEventsViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }


    /**
     * Display error message when importing event fails.
     * @param error
     */
    @Override
    public void prepareFailView(String error){
        ImportEventsState importEventsState = importEventsViewModel.getState();
        importEventsState.setImportEventError(error);
        importEventsViewModel.firePropertyChanged();
    }

    /**
     * Switch to Home View
     * */
    @Override
    public void switchToHome() {
        importEventsViewModel.setState(new ImportEventsState());
        importEventsViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(listViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
