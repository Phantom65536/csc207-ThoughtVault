package view.user;

import interface_adapter.log_in_out.LogInOutController;
import interface_adapter.log_in_out.LogInViewModel;
import view.LabelTextPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class LogInView extends JPanel implements ActionListener, PropertyChangeListener {
    private final LogInViewModel loginViewModel;

    final JTextField usernameInputField = new JTextField(15);
    private final JLabel usernameErrorField = new JLabel();

    final JPasswordField passwordInputField = new JPasswordField(15);
    private final JLabel passwordErrorField = new JLabel();

    final JButton logIn;
    final JButton switchToSignupView;
    private final LogInOutController loginoutController;

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
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
