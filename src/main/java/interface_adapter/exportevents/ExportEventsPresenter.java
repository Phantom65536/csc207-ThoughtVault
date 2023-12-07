package interface_adapter.exportevents;

import interface_adapter.ViewManagerModel;
import interface_adapter.listView.ListViewModel;
import use_case.gcalevent.GCalEventOutputBoundary;
import use_case.gcalevent.GCalEventOutputData;

/**
 * Presenter class for exporting events
 */
public class ExportEventsPresenter implements GCalEventOutputBoundary {
    private final ExportEventsViewModel exportEventsViewModel;
    private final ListViewModel listViewModel;
    private ViewManagerModel viewManagerModel;
    /**
     * Instantiate a new ExportEventsPresenter which uses the following view models.
     * @param exportEventsViewModel
     * @param listViewModel
     * @param viewManagerModel
     */
    public ExportEventsPresenter(ExportEventsViewModel exportEventsViewModel, ListViewModel listViewModel, ViewManagerModel viewManagerModel){
        this.exportEventsViewModel = exportEventsViewModel;
        this.listViewModel = listViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    /**
     * Reset export event state if exporting event is successful
     * @param response
     */
    @Override
    public void prepareSuccessView(GCalEventOutputData response) {
        ExportEventsState exportEventsState = exportEventsViewModel.getState();
        exportEventsState.setExportedEventSummary(response.getTitle());
        exportEventsViewModel.setState(exportEventsState);
        exportEventsViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(exportEventsViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    /**
     * Display error message when exporting event fails.
     * @param error
     */
    @Override
    public void prepareFailView(String error){
        ExportEventsState exportEventsState = exportEventsViewModel.getState();
        exportEventsState.setExportEventError(error);
        exportEventsViewModel.firePropertyChanged();
    }

    /**
     * Switch to Home View
     * */
    @Override
    public void switchToHome() {
        exportEventsViewModel.setState(new ExportEventsState());
        exportEventsViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(listViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
