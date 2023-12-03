package interface_adapter.exportevents;

import interface_adapter.ViewManagerModel;
import interface_adapter.home.HomeViewModel;
import use_case.GCalEventOutputBoundary;
import use_case.GCalEventOutputData;

public class ExportEventsPresenter implements GCalEventOutputBoundary {
    private final ExportEventsViewModel exportEventsViewModel;
    private final HomeViewModel homeViewModel;
    private ViewManagerModel viewManagerModel;
    public ExportEventsPresenter(ExportEventsViewModel exportEventsViewModel, HomeViewModel homeViewModel, ViewManagerModel viewManagerModel){
        this.exportEventsViewModel = exportEventsViewModel;
        this.homeViewModel = homeViewModel;
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

    @Override
    public void switchToHome() {
        exportEventsViewModel.setState(new ExportEventsState());
        exportEventsViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(homeViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
