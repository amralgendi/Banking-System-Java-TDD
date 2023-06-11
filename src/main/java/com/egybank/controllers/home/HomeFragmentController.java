//
//  HomeFragmentController.java
//  Studyico
//
//  Created by Mohamed Salah on 4/24/2022.
//

package com.egybank.controllers.home;

import com.egybank.controllers.SceneController;
import com.egybank.helpers.auth.Authentication;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class HomeFragmentController {
    @FXML
    protected Pane root;

    @FXML
    protected void onDepositClicked(MouseEvent mouseEvent) {
        SceneController.showDepositScene(mouseEvent);
    }
    @FXML
    protected void onWithdrawClicked(MouseEvent mouseEvent) {
        SceneController.showWithdrawScene(mouseEvent);
    }
    @FXML
    protected void onTransferClicked(MouseEvent mouseEvent) {
        SceneController.showTransferScene(mouseEvent);
    }
    @FXML
    protected void onBillsClicked(MouseEvent mouseEvent) {
        SceneController.showBillsScene(mouseEvent);
    }
    @FXML
    protected void onBuyClicked(MouseEvent mouseEvent) {
        SceneController.showBuyScene(mouseEvent);
    }
    @FXML
    protected void onAccountClicked(MouseEvent mouseEvent) {
        SceneController.showAcountScene(mouseEvent);
    }
    @FXML
    private void onLogoutClicked(MouseEvent mouseEvent) {
        SceneController.showLoginScene(mouseEvent);
        Authentication.logout();
    }
}
