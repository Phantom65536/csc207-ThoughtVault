package use_case;

public interface GCalEventOutputBoundary {
    void prepareSuccessView(GCalEventOutputData gCalEventOutputData);
    void prepareFailView(String error);
}
