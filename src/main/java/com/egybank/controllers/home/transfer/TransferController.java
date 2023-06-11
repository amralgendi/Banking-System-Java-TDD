package com.egybank.controllers.home.transfer;

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

public class TransferController extends HomeFragmentController implements UserObjectUpdated {


    public Label availableAmountLabel;
    public TextField amountField;
    public TextField receivingAccountNoField;

    @FXML
    public void initialize() {
        Platform.runLater( () -> root.requestFocus() );

        Authentication.setUserObjectUpdated(this);
        updateAvailableBalance();
    }
    @Override
    protected void onTransferClicked(MouseEvent mouseEvent) {

    }

    public void transfer(Integer fromUserId, Integer toUserId, Integer amount){
        User fromUser = UserDataAccess.getUser(fromUserId);
        User toUser = UserDataAccess.getUser(toUserId);

        if (toUser == null) {
            // AlertsHelper.showAlert(Alert.AlertType.ERROR, "Error", "Receiving account number is invalid!");
            throw new IllegalArgumentException("Invalid Account Id");
        }

        if (fromUser == null) {
            throw new IllegalArgumentException("No Main User");
        }

        if (fromUser.getBalance() < amount) {
            throw new IllegalArgumentException("Insufficient Balance");
        }

        Transaction withDrawTransaction = new Transaction(0,
                "Transfer to account #" + toUserId,
                fromUserId,
                toUserId,
                amount,
                TransactionType.TRANSFER.getIntRepresentation());

        UserDataAccess.transfer(Authentication.getCurrentUser().getId(), toUserId, amount);
        TransactionDataAccess.createNewTransaction(withDrawTransaction);
    }

    public void onTransferBtnClicked(MouseEvent mouseEvent) {
        int transferAmount = Integer.parseInt(amountField.getText());
        int toAccountId = Integer.parseInt(receivingAccountNoField.getText());

        try {
            transfer(Authentication.getCurrentUser().getId(), toAccountId, transferAmount);

            updateAvailableBalance();
            AlertsHelper.showAlert(Alert.AlertType.CONFIRMATION, "Success", "Amount transferred successfully!");
        }
        catch (IllegalArgumentException e){
            AlertsHelper.showAlert(Alert.AlertType.ERROR, "Error", e.getMessage());
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
