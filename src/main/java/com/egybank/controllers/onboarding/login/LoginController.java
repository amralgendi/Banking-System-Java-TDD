package com.egybank.controllers.onboarding.login;

import com.egybank.controllers.SceneController;
import com.egybank.helpers.AlertsHelper;
import com.egybank.helpers.auth.Authentication;
import com.egybank.helpers.auth.LoginCallback;
import com.egybank.models.User;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class LoginController {
    @FXML
    private Pane root;

    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passwordField;

    @FXML
    protected void onSignupButtonClicked(MouseEvent event) throws IOException {
        SceneController.showSignupScene(event);
    }

    @FXML
    protected void onLoginButtonClicked(MouseEvent event) {
        String email = emailField.getText();
        String password = passwordField.getText();

        if (email.equals("") || password.equals("")) {
            AlertsHelper.showAlert(Alert.AlertType.ERROR, "Missing Fields", "Please enter your email AND password!");
            return;
        }
        Authentication.login(email, password, new LoginCallback() {
            @Override
            public void onLoginSuccess(User user) {
                SceneController.showDepositScene(event);
            }

            @Override
            public void onLoginError() {
                AlertsHelper.showAlert(Alert.AlertType.ERROR, "Invalid Credentials", "Please check for your email or password!");
            }
        });
    }

    @FXML
    public void initialize() {
        Platform.runLater( () -> root.requestFocus() );
    }
}