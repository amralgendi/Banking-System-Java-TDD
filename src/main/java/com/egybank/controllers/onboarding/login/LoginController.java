package com.egybank.controllers.onboarding.login;

import com.egybank.controllers.SceneController;
import com.egybank.helpers.AlertsHelper;
import com.egybank.helpers.auth.Authentication;
import com.egybank.helpers.auth.LoginCallback;
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

    public User login(String email, String password){
        if (email.equals("") || password.equals("")) {
            throw new IllegalArgumentException("Please enter both email and password");
        }

        User user = UserDataAccess.getUser(email, password);
        if(user == null){
            throw new NullPointerException("No User Found!");
        }
        return user;
    }

    @FXML
    protected void onLoginButtonClicked(MouseEvent event) {
        String email = emailField.getText();
        String password = passwordField.getText();

        try {
            User user = login(email, password);
            Authentication.login(user, new LoginCallback() {
                @Override
                public void onLoginSuccess(User user) {
                    SceneController.showDepositScene(event);
                }
            });
        }
        catch (IllegalArgumentException e){
            AlertsHelper.showAlert(Alert.AlertType.ERROR, "Missing Fields", e.getMessage());
        }
        catch (NullPointerException e){
            AlertsHelper.showAlert(Alert.AlertType.ERROR, "Invalid Credentials", e.getMessage());
        }
    }

    @FXML
    public void initialize() {
        Platform.runLater( () -> root.requestFocus() );
    }
}