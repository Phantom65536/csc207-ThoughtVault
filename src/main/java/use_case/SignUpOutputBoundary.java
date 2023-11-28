package use_case;

public interface SignUpOutputBoundary {
    void prepareSuccessView(int userid);
    void prepareFailView(String error);
}
