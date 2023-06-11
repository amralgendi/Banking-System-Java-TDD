package com.egybank.controllers.home.account;

import com.egybank.controllers.home.HomeFragmentController;
import com.egybank.helpers.auth.Authentication;
import com.egybank.helpers.db.dao.TransactionDataAccess;
import com.egybank.models.Transaction;
import com.egybank.models.User;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;

import java.util.ArrayList;

public class AccountController extends HomeFragmentController {
    public FlowPane transactionsFlowPane;
    public Label balanceLabel;
    public Label helloLabel;

    @FXML
    public void initialize() {
        Platform.runLater( () -> root.requestFocus() );
        updateTransactionsFlowPane();
        updateUserInfo();
    }

    @Override
    protected void onAccountClicked(MouseEvent mouseEvent) {

    }

    private void updateUserInfo() {
        User user = Authentication.getCurrentUser();
        helloLabel.setText("Hello, "+ user.getName() + ". #" + user.getId());
        balanceLabel.setText("Available balance: $"+Authentication.getCurrentUser().getBalance());
    }

    private void updateTransactionsFlowPane() {
        ArrayList<Transaction> transactions = TransactionDataAccess.getTransactions(Authentication.getCachedCurrentUser().getId());
        addTransactionsToFlowPane(transactions);
    }
    private void addTransactionsToFlowPane(ArrayList<Transaction> transactions) {
        transactionsFlowPane.getChildren().removeAll(transactionsFlowPane.getChildren());

        for (Transaction file: transactions) {
            TransactionItemView itemView = new TransactionItemView();
            itemView.configure(file);
            transactionsFlowPane.getChildren().add(itemView);
        }
    }
}
