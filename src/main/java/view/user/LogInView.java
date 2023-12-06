package view.user;

import interface_adapter.log_in_out.LogInOutController;
import interface_adapter.log_in_out.LogInState;
import interface_adapter.log_in_out.LogInViewModel;
import view.LabelTextPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.security.GeneralSecurityException;

/**
 * View class for logging in.
 */
public class LogInView extends JPanel implements ActionListener, PropertyChangeListener {
    private final LogInViewModel loginViewModel;

    final JTextField usernameInputField = new JTextField(15);
    final JPasswordField passwordInputField = new JPasswordField(15);

    final JButton logIn;
    final JButton switchToSignupView;
    private final LogInOutController loginoutController;

    /**
     * Instantiate a LogInView by adding the necessary labels, input fields and buttons.
     * @param loginViewModel
     * @param controller
     */
    public LogInView(LogInViewModel loginViewModel, LogInOutController controller) {
        this.loginViewModel = loginViewModel;
        this.loginoutController = controller;
        this.loginViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(LogInViewModel.TITLE_VIEW);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel usernameInfo = new LabelTextPanel(
                new JLabel(LogInViewModel.USERNAME_LABEL), usernameInputField);
        LabelTextPanel passwordInfo = new LabelTextPanel(
                new JLabel(LogInViewModel.PASSWORD_LABEL), passwordInputField);

        JPanel buttons = new JPanel();
        logIn = new JButton(LogInViewModel.LOGIN_BUTTON_LABEL);
        buttons.add(logIn);
        switchToSignupView = new JButton(LogInViewModel.SWITCH_VIEW_LABEL);
        buttons.add(switchToSignupView);

        logIn.addActionListener(                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    /**
                     * Check if the text input fields are empty and if not log into the system with the controller.
                     * @param evt the event to be processed
                     */
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(logIn)) {
                            LogInState currentState = loginViewModel.getState();
                            if (currentState.getUsername() == null || currentState.getPassword() == null) {
                                JOptionPane.showMessageDialog(null, "Please fill in all fields.");
                            } else {
                                try {
                                    loginoutController.logIn(
                                            currentState.getUsername(),
                                            currentState.getPassword()
                                    );
                                } catch (GeneralSecurityException | IOException e) {
                                    JOptionPane.showMessageDialog(null, "GcalDAO setUserCalendar throws " + e.getMessage());
                                    System.out.println(e.getMessage());
                                }
                            }
                        }
                    }
                }
        );

        switchToSignupView.addActionListener(
                new ActionListener() {
                    /**
                     * Switch to SignUpView when this button is clicked.
                     * @param e the event to be processed
                     */
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(switchToSignupView)) {
                            loginoutController.switchToSignupView();
                        }
                    }
                }
        );

        usernameInputField.addKeyListener(
            new KeyListener() {
                /**
                 * Update username in the corresponding view model state
                 * @param e the event to be processed
                 */
                @Override
                public void keyTyped(KeyEvent e) {
                    LogInState currentState = loginViewModel.getState();
                    String text = usernameInputField.getText() + e.getKeyChar();
                    currentState.setUsername(text);
                    loginViewModel.setState(currentState);
                }

                @Override
                public void keyPressed(KeyEvent e) {
                }

                @Override
                public void keyReleased(KeyEvent e) {
                }
            }
        );

        passwordInputField.addKeyListener(
                new KeyListener() {
                    /**
                     * Update password in the corresponding view model state
                     * @param e the event to be processed
                     */
                    @Override
                    public void keyTyped(KeyEvent e) {
                        LogInState currentState = loginViewModel.getState();
                        currentState.setPassword(String.valueOf(passwordInputField.getPassword()) + e.getKeyChar());
                        loginViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {

                    }

                    @Override
                    public void keyReleased(KeyEvent e) {

                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(usernameInfo);
        this.add(passwordInfo);
        this.add(buttons);
    }

    /**
     * Default action of unprocessed buttons.
     * @param evt the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    /**
     * Display error message when an event notification is detected.
     * @param evt A PropertyChangeEvent object describing the event source
     *          and the property that has changed.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        LogInState logInState = (LogInState) evt.getNewValue();
        if (logInState.getUsernameError() != null) {
            JOptionPane.showMessageDialog(null, logInState.getUsernameError());
        } else if (logInState.getPasswordError() != null) {
            JOptionPane.showMessageDialog(null, logInState.getPasswordError());
        }
    }
}
