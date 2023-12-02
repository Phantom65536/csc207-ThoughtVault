package interface_adapter.exportevents;

import interface_adapter.ViewManagerModel;
import use_case.gcalevent.GCalEventOutputBoundary;
import use_case.gcalevent.GCalEventOutputData;

public class ExportEventsPresenter implements GCalEventOutputBoundary {
    private final ExportEventsViewModel exportEventsViewModel;
    private ViewManagerModel viewManagerModel;
    public ExportEventsPresenter(ExportEventsViewModel exportEventsViewModel, ViewManagerModel viewManagerModel){
        this.exportEventsViewModel = exportEventsViewModel;
        this.viewManagerModel = viewManagerModel;
    }
    @Override
    public void prepareSuccessView(GCalEventOutputData response) {
        ExportEventsState exportEventsState = exportEventsViewModel.getState();
        exportEventsState.setExportedEventSummary(response.getTitle());
        exportEventsViewModel.setState(exportEventsState);
        exportEventsViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(exportEventsViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error){
        ExportEventsState exportEventsState = exportEventsViewModel.getState();
        exportEventsState.setExportEventError(error);
        exportEventsViewModel.firePropertyChanged();
    }
}
