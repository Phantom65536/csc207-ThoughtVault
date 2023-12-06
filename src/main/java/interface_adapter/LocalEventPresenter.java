package interface_adapter;

import use_case.local_event.LocalEventOutputData;

import use_case.local_event.LocalEventOutputBoundary;

import java.util.ArrayList;

import java.util.HashMap;

public class LocalEventPresenter implements LocalEventOutputBoundary {
    private final LocalEventViewModel detailedLocalEventViewModel;

    private final LocalEventViewModel localEventCreationViewModel;

    private final LocalEventViewModel localEventEditViewModel;

    private final ListViewModel listViewModel;

    private final ViewManagerModel viewManagerModel;

    public LocalEventPresenter(ViewManagerModel viewManagerModel,
                               LocalEventViewModel detailedLocalEventViewModel,
                               LocalEventViewModel localEventCreationViewModel,
                               LocalEventViewModel localEventEditViewModel,
                               ListViewModel listViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.detailedLocalEventViewModel = detailedLocalEventViewModel;
        this.localEventCreationViewModel = localEventCreationViewModel;
        this.localEventEditViewModel = localEventEditViewModel;
        this.listViewModel = listViewModel;
    }

    public LocalEventState CreateState(LocalEventOutputData localEventOutputData) {
        return new LocalEventState(localEventOutputData.getTitle(),
                localEventOutputData.getLocation(),
                localEventOutputData.getDescription(),
                localEventOutputData.getIsWork(),
                localEventOutputData.getPinned(),
                localEventOutputData.getSubEvents(),
                localEventOutputData.getDate(),
                localEventOutputData.getStartTime(),
                localEventOutputData.getEndTime(),
                localEventOutputData.getUserId());
    }

    @Override
    public void UpdateEventsList(LocalEventOutputData
                                                   localEventOutputData) {
        ListViewState listViewState = listViewModel.getState();
        HashMap<Integer, HashMap<String, ?>> listViewEvents =
                listViewState.getEvents();
        listViewEvents.put(localEventOutputData.getID(),
                localEventOutputData.getEventData());
        listViewState.setEvents(listViewEvents);
        listViewModel.firePropertyChanged();
        DisplayEventDetailedView(localEventOutputData);
    }

    @Override
    public void DeleteEventSuccessView(int ID) {
        ListViewState listViewState = listViewModel.getState();
        HashMap<Integer, HashMap<String, ?>> localEventViewModelArrayList =
                listViewState.getEvents();
        localEventViewModelArrayList.remove(ID);
        listViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(listViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void EventFailView(String errorMessage) {
        System.out.println(errorMessage);
    }

    @Override
    public void DisplayEventDetailedView(LocalEventOutputData localEventOutputData) {
        LocalEventState localEventState = CreateState(localEventOutputData);
        detailedLocalEventViewModel.setState(localEventState);
        detailedLocalEventViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(
                detailedLocalEventViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void DisplayEventCreationView() {
        LocalEventState localEventState = new LocalEventState();
        localEventCreationViewModel.setState(localEventState);
        localEventCreationViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(
                localEventCreationViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void DisplayEventEditView(LocalEventOutputData eventOutputData) {
        LocalEventState localEventState = CreateState(eventOutputData);
        localEventEditViewModel.setState(localEventState);
        localEventEditViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(localEventEditViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void DisplayAllEvents(ArrayList<LocalEventOutputData>
                                             localEventOutputDataArrayList) {
        HashMap<Integer, HashMap<String, ?>> listViewEvents =
                new HashMap<Integer, HashMap<String, ?>>();
        for (LocalEventOutputData localEventOutputData :
                localEventOutputDataArrayList) {
            listViewEvents.put(localEventOutputData.getID(),
                    localEventOutputData.getEventData());
        }
        ListViewState listViewState = listViewModel.getState();
        listViewState.setEvents(listViewEvents);
        listViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(listViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
