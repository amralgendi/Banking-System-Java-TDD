package com.egybank.controllers.onboarding.welcome;

import com.egybank.controllers.SceneController;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class WelcomeController {
    @FXML
    private Pane root;

    @FXML
    protected void onSignUpClicked(MouseEvent event) {
        SceneController.showSignupScene(event);
    }

    @FXML
    protected void onLoginClicked(MouseEvent event) {
        SceneController.showLoginScene(event);
    }
}