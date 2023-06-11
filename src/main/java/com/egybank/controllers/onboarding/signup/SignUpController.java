package com.egybank.controllers.onboarding.signup;

import com.egybank.controllers.SceneController;
import com.egybank.helpers.AlertsHelper;
import com.egybank.helpers.SignUpValidation;
import com.egybank.helpers.auth.Authentication;
import com.egybank.helpers.auth.SignUpCallback;
import com.egybank.helpers.db.dao.UserDataAccess;
import com.egybank.models.User;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class SignUpController {
    @FXML
    private Pane root;
    @FXML
    private TextField nameField;
    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private PasswordField verifyPasswordField;

    public User signup(String name, String email, String password, String verifyPassword){
        if (name.equals("") || email.equals("") || password.equals("") || verifyPassword.equals("")) {
            throw new IllegalArgumentException("All fields are required.");
        }

        if (!SignUpValidation.isValidEmailAddress(email)) {
            throw new IllegalArgumentException("Please make sure that the email is correct.");
        }

        if (!password.equals(verifyPassword)) {
            throw new IllegalArgumentException("Password fields have different values.");
        }

        if (password.length() < 8) {
            throw new IllegalArgumentException("Password must be 8 characters or more.");
        }

        if (UserDataAccess.isUserAvailable(email)) {
            throw new IllegalArgumentException("The email provided has an account associated with, please login instead.");
        }

        User user = new User(0, name, email, password, 0);

        Integer id = UserDataAccess.createNewUser(user);

        if(id == null){
            throw new NullPointerException("Something went wrong please try again later.");
        }

        User newUser = UserDataAccess.getUser(id);

        return newUser;

    }

    @FXML
    private void onSignupButtonClicked(MouseEvent event) {

        String name = nameField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();
        String verifyPassword = verifyPasswordField.getText();

        try{
            User user = signup(name, email, password, verifyPassword);

            Authentication.signUpAndLogin(user, new SignUpCallback() {
                @Override
                public void onSignUpSuccess(User user) {
                    SceneController.showDepositScene(event);
                }

            });

        }
        catch (IllegalArgumentException e){
            AlertsHelper.showAlert(Alert.AlertType.ERROR, "Error", e.getMessage());
        }
        catch (NullPointerException e){
            AlertsHelper.showAlert(Alert.AlertType.ERROR, "Error", e.getMessage());
        }


    }


    @FXML
    protected void onLoginButtonClicked(MouseEvent event) throws IOException {
        SceneController.showLoginScene(event);
    }

    @FXML
    public void initialize() {
        Platform.runLater( () -> root.requestFocus() );
    }
}