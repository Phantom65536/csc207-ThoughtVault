package view.user;

import interface_adapter.log_in_out.LogInState;
import interface_adapter.sign_up.SignUpController;
import interface_adapter.sign_up.SignUpState;
import interface_adapter.sign_up.SignUpViewModel;
import view.LabelTextPanel;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;

public class SignUpView extends JPanel implements ActionListener, PropertyChangeListener {
    private final SignUpViewModel signUpViewModel;
    private final JTextField usernameInputField = new JTextField(15);
    private final JPasswordField passwordInputField = new JPasswordField(15);
    private final JPasswordField repeatPasswordInputField = new JPasswordField(15);
    private final JTextArea credentialsStringInputField = new JTextArea(10, 45);
//    private final JFileChooser credentialsFileInputField = new JFileChooser();
    private final SignUpController signUpController;

//    private final JButton chooseCredentialFile;
    private final JButton signUp;
    private final JButton switchToLoginView;

    public SignUpView(SignUpViewModel viewModel, SignUpController controller) {
        this.signUpViewModel = viewModel;
        this.signUpController = controller;
        signUpViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(SignUpViewModel.TITLE_VIEW);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel usernameInfo = new LabelTextPanel(
                new JLabel(SignUpViewModel.USERNAME_LABEL), usernameInputField);
        LabelTextPanel passwordInfo = new LabelTextPanel(
                new JLabel(SignUpViewModel.PASSWORD_LABEL), passwordInputField);
        LabelTextPanel repeatPasswordInfo = new LabelTextPanel(
                new JLabel(SignUpViewModel.REPEAT_PASSWORD_LABEL), repeatPasswordInputField);
        JLabel credentialsInputInfo = new JLabel(SignUpViewModel.CREDENTIALS_STRING_LABEL);

//        credentialsFileInputField.setFileSelectionMode(JFileChooser.FILES_ONLY);
//        credentialsFileInputField.setAcceptAllFileFilterUsed(false);
//        credentialsFileInputField.addChoosableFileFilter(
//                new FileFilter() {
//                    @Override
//                    public boolean accept(File f) {
//                        return !f.isDirectory() && f.getName().toLowerCase().endsWith(".txt");
//                    }
//
//                    @Override
//                    public String getDescription() {
//                        return "Text Files (*.txt)";
//                    }
//                }
//        );

        JPanel buttons = new JPanel();
//        chooseCredentialFile = new JButton(SignUpViewModel.CREDENTIALS_UPLOAD_LABEL);
//        buttons.add(chooseCredentialFile);
        signUp = new JButton(SignUpViewModel.SIGNUP_BUTTON_LABEL);
        buttons.add(signUp);
        switchToLoginView = new JButton(SignUpViewModel.SWITCH_VIEW_LABEL);
        buttons.add(switchToLoginView);

//        chooseCredentialFile.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent evt) {
//                if (evt.getSource().equals(chooseCredentialFile)) {
//                    if (credentialsFileInputField.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
//                        try (BufferedReader br = )
//                    }
//                    SignUpState signUpState = signUpViewModel.getState();
//                }
//            }
//        });

        signUp.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(signUp)) {
                            SignUpState currentState = signUpViewModel.getState();
                            if (currentState.getUsername().isEmpty() || currentState.getPassword().isEmpty() ||
                                    currentState.getRepeatedPassword().isEmpty() || currentState.getCredentialsJSON().isEmpty()) {
                                JOptionPane.showMessageDialog(null, "Please fill in all fields.");
                            } else {
                                    signUpController.signUp(
                                            currentState.getUsername(),
                                            currentState.getPassword(),
                                            currentState.getRepeatedPassword(),
                                            currentState.getCredentialsJSON()
                                    );
                            }
                        }
                    }
                }
        );

        switchToLoginView.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource().equals(switchToLoginView)) {
                    signUpController.switchToLoginView();
                }
            }
        });

        usernameInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        SignUpState currentState = signUpViewModel.getState();
                        String text = usernameInputField.getText() + e.getKeyChar();
                        currentState.setUsername(text);
                        signUpViewModel.setState(currentState);
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
                    @Override
                    public void keyTyped(KeyEvent e) {
                        SignUpState currentState = signUpViewModel.getState();
                        currentState.setPassword(String.valueOf(passwordInputField.getPassword()) + e.getKeyChar());
                        signUpViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {

                    }

                    @Override
                    public void keyReleased(KeyEvent e) {

                    }
                }
        );

        repeatPasswordInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        SignUpState currentState = signUpViewModel.getState();
                        currentState.setRepeatedPassword(String.valueOf(repeatPasswordInputField.getPassword()) + e.getKeyChar());
                        signUpViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {

                    }

                    @Override
                    public void keyReleased(KeyEvent e) {

                    }
                }
        );

        credentialsStringInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        SignUpState currentState = signUpViewModel.getState();
                        String text = credentialsStringInputField.getText() + e.getKeyChar();
                        currentState.setCredentialsJSON(text);
                        signUpViewModel.setState(currentState);
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
        this.add(repeatPasswordInfo);
        this.add(credentialsInputInfo);
        this.add(credentialsStringInputField);
        this.add(buttons);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        SignUpState signUpState = signUpViewModel.getState();
        if (signUpState.getUsernameError() != null) {
            JOptionPane.showMessageDialog(null, signUpState.getUsernameError());
        } else if (signUpState.getPasswordError() != null) {
            JOptionPane.showMessageDialog(null, signUpState.getPasswordError());
        } else if (signUpState.getCredentialsError() != null) {
            JOptionPane.showMessageDialog(null, signUpState.getCredentialsError());
        }
    }
}
