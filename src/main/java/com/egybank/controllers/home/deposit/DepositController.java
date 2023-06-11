package com.egybank.controllers.home.deposit;

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

public class DepositController extends HomeFragmentController implements UserObjectUpdated {

    public Label availableAmountLabel;
    public TextField amountField;

    @FXML
    public void initialize() {
        Platform.runLater( () -> root.requestFocus() );

        Authentication.setUserObjectUpdated(this);
        updateAvailableBalance();
    }


    @Override
    protected void onDepositClicked(MouseEvent mouseEvent) {

    }

    public void deposit(Integer userId, Integer amount){
        Transaction withDrawTransaction = new Transaction(0,
                "Deposit from online banking platform",
                userId,
                -1,
                amount,
                TransactionType.DEPOSIT.getIntRepresentation());

        UserDataAccess.deposit(userId, amount);
        TransactionDataAccess.createNewTransaction(withDrawTransaction);
    }

    public void onDepositBtnClicked(MouseEvent mouseEvent) {

        if(!isParsable(amountField.getText())){
            AlertsHelper.showAlert(Alert.AlertType.ERROR, "Invalid Integer", "Please enter a valid Amount!");
            return;
        }

        int depositedValue = Integer.parseInt(amountField.getText()) ;



        // TODO: - Add checking logic
        if (1!=1) {
            return;
        }

        deposit(Authentication.getCurrentUser().getId(), depositedValue);

        updateAvailableBalance();

        AlertsHelper.showAlert(Alert.AlertType.CONFIRMATION, "Success", "Amount deposited successfully!");
    }

    private void updateAvailableBalance() {
        if (Authentication.getCachedCurrentUser() == null) { return; }
        availableAmountLabel.setText("Available Balance: $"+ Authentication.getCurrentUser().getBalance());
    }

    public static boolean isParsable(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (final NumberFormatException e) {
            return false;
        }
    }


    @Override
    public void onUserUpdated(User user) {
        updateAvailableBalance();
    }
}
