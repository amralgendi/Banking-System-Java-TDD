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

    @FXML
    private void onSignupButtonClicked(MouseEvent event) {

        String name = nameField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();
        String verifyPassword = verifyPasswordField.getText();

        if (name.equals("") || email.equals("") || password.equals("") || verifyPassword.equals("")) {
            AlertsHelper.showAlert(Alert.AlertType.ERROR, "Missing Fields", "All fields are required.");
            return;
        }

        if (!SignUpValidation.isValidEmailAddress(email)) {
            AlertsHelper.showAlert(Alert.AlertType.ERROR, "Invalid Email", "Please make sure that the email is correct.");
            return;
        }

        if (!password.equals(verifyPassword)) {
            AlertsHelper.showAlert(Alert.AlertType.ERROR, "Password mismatch", "Password fields have different values.");
            return;
        }

        if (password.length() < 8) {
            AlertsHelper.showAlert(Alert.AlertType.ERROR, "Weak Password", "Password must be 8 characters or more.");
            return;
        }

        if (UserDataAccess.isUserAvailable(email)) {
            AlertsHelper.showAlert(Alert.AlertType.ERROR, "Account exists", "The email provided has an account associated with, please login instead.");
            return;
        }

        if (UserDataAccess.isUserAvailable(email)) {
            AlertsHelper.showAlert(Alert.AlertType.ERROR, "Account exists", "The email provided has an account associated with, please login instead.");
            return;
        }

        User newUserObj = new User(0, name, email, password, 0);
        Authentication.signUpAndLogin(newUserObj, new SignUpCallback() {
            @Override
            public void onSignUpSuccess(User user) {
                SceneController.showDepositScene(event);
            }

            @Override
            public void onSignUpError() {
                AlertsHelper.showAlert(Alert.AlertType.ERROR, "Sorry", "Something went wrong please try again later.");
            }
        });

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