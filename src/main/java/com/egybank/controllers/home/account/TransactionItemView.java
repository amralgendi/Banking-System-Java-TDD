//
//  FileItemView.java
//  Studyico
//
//  Created by Mohamed Salah on 4/30/2022.
//

package com.egybank.controllers.home.account;

import com.egybank.models.Transaction;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TransactionItemView extends Pane implements Initializable {
    @FXML
    private Label transactionNameLabel, transactionNumberLabel, transactionAmountLabel;
    public TransactionItemView() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/layout/transaction_item_view.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) { }

    private Transaction transaction;
    public void configure(Transaction transaction) {
        this.transaction = transaction;

        transactionNameLabel.setText(transaction.getName());
        transactionNumberLabel.setText("#"+transaction.getId());
        transactionAmountLabel.setText("$"+transaction.getAmount());
    }

}
