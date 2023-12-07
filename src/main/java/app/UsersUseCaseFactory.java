package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.listView.ListViewModel;
import interface_adapter.localEvent.LocalEventController;
import interface_adapter.log_in_out.LogInOutController;
import interface_adapter.log_in_out.LogInOutPresenter;
import interface_adapter.log_in_out.LogInViewModel;
import interface_adapter.note.NoteController;
import interface_adapter.sign_up.SignUpController;
import interface_adapter.sign_up.SignUpPresenter;
import interface_adapter.sign_up.SignUpViewModel;
import use_case.gcalevent.GCalEventDataAccessInterface;
import use_case.user.*;
import view.user.LogInView;
import view.user.SignUpView;

import java.beans.PropertyChangeListener;

public class UsersUseCaseFactory {
    private UsersUseCaseFactory() {}

    /**
     * Create SignUpView and LogInView.
     * @param viewManagerModel
     * @param logInViewModel
     * @param signUpViewModel
     * @param listViewModel
     * @param userDataAccessInterface
     * @param gcalDataAccessInterface
     * @param noteController
     * @param localEventController
     * @return
     */
    public static PropertyChangeListener[] create(
            ViewManagerModel viewManagerModel, LogInViewModel logInViewModel, SignUpViewModel signUpViewModel, ListViewModel listViewModel,
            UserDataAccessInterface userDataAccessInterface, GCalEventDataAccessInterface gcalDataAccessInterface,
            NoteController noteController, LocalEventController localEventController
    ) {
        SignUpController signUpController = createUserSignupUseCase(viewManagerModel, signUpViewModel, logInViewModel, userDataAccessInterface);
        LogInOutController logInOutController = createUserLoginoutCase(viewManagerModel, signUpViewModel, logInViewModel, listViewModel, userDataAccessInterface, gcalDataAccessInterface);

        return new PropertyChangeListener[]{new SignUpView(signUpViewModel, signUpController), new LogInView(logInViewModel, logInOutController, listViewModel, noteController, localEventController)};
    }

    private static SignUpController createUserSignupUseCase(ViewManagerModel viewManagerModel, SignUpViewModel signUpViewModel,
                                                            LogInViewModel logInViewModel, UserDataAccessInterface userDataAccessObject) {
        SignUpOutputBoundary signUpOutputBoundary = new SignUpPresenter(logInViewModel, signUpViewModel, viewManagerModel);
        SignUpInputBoundary signUpInteractor = new SignUpInteractor(userDataAccessObject, signUpOutputBoundary);
        return new SignUpController(signUpInteractor);
    }

    private static LogInOutController createUserLoginoutCase(ViewManagerModel viewManagerModel, SignUpViewModel signUpViewModel,
                                                             LogInViewModel logInViewModel, ListViewModel listViewModel, UserDataAccessInterface userDataAccessObject,
                                                             GCalEventDataAccessInterface gcalDataAccessInterface) {
        LogInOutOutputBoundary logInOutOutputBoundary = new LogInOutPresenter(logInViewModel, signUpViewModel, listViewModel, viewManagerModel);
        LogInOutInputBoundary logInOutInteractor = new LogInOutInteractor(userDataAccessObject, gcalDataAccessInterface, logInOutOutputBoundary);
        return new LogInOutController(logInOutInteractor);
    }
}
