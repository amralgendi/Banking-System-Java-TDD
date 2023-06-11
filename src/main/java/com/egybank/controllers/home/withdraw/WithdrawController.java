package com.egybank.controllers.home.withdraw;

import com.egybank.controllers.home.HomeFragmentController;
import com.egybank.controllers.home.UserObjectUpdated;
import com.egybank.helpers.AlertsHelper;
import com.egybank.helpers.auth.Authentication;
import com.egybank.helpers.db.dao.TransactionDataAccess;
import com.egybank.helpers.db.dao.TransactionType;
import com.egybank.helpers.db.dao.UserDataAccess;
import com.egybank.models.Transaction;
import com.egybank.models.User;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class WithdrawController extends HomeFragmentController implements UserObjectUpdated {

    public Label availableAmountLabel;
    public TextField amountField;

    @FXML
    public void initialize() {
        Platform.runLater( () -> root.requestFocus() );

        Authentication.setUserObjectUpdated(this);
        updateAvailableBalance();
    }


    @Override
    protected void onWithdrawClicked(MouseEvent mouseEvent) {

    }

    public void withdraw(Integer userId, Integer amount){
        User user = UserDataAccess.getUser(userId);
        if (user.getBalance() < amount) {
            throw new IllegalArgumentException("Insufficient Balance");
        }

        Transaction withDrawTransaction = new Transaction(0,
                "Withdraw from online banking platform",
                userId,
                -1,
                amount,
                TransactionType.WITHDRAW.getIntRepresentation());

        UserDataAccess.withdraw(userId, amount);
        TransactionDataAccess.createNewTransaction(withDrawTransaction);
    }

    public void onWithdrawBtnClicked(MouseEvent mouseEvent) {
        int withdrawnValue = Integer.parseInt(amountField.getText());

        try {
            withdraw(Authentication.getCurrentUser().getId(), withdrawnValue);

            updateAvailableBalance();
            AlertsHelper.showAlert(Alert.AlertType.CONFIRMATION, "Success", "Amount withdrawn successfully!");
        }
        catch (IllegalArgumentException e){
            AlertsHelper.showAlert(Alert.AlertType.ERROR, "Invalid", e.getMessage());
            return;
        }
    }

    private void updateAvailableBalance() {
        if (Authentication.getCachedCurrentUser() == null) { return; }
        availableAmountLabel.setText("Available Balance: $"+ Authentication.getCurrentUser().getBalance());
    }

    @Override
    public void onUserUpdated(User user) {
        updateAvailableBalance();
    }
}
