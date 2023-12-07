package use_case.gcalevent;

public interface GCalEventOutputBoundary {
    void prepareSuccessView(GCalEventOutputData gCalEventOutputData);
    void prepareFailView(String error);
    void switchToHome();
}
