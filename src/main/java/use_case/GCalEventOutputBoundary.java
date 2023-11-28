package use_case;

import OutputData.GCalEventOutputData;

public interface GCalEventOutputBoundary {
    void prepareSuccessView(GCalEventOutputData gCalEventOutputData);
    void prepareFailView(String error);
}
