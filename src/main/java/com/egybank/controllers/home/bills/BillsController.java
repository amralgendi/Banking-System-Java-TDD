package com.egybank.controllers.home.bills;

import com.egybank.controllers.home.HomeFragmentController;
import com.egybank.helpers.AlertsHelper;
import com.egybank.helpers.auth.Authentication;
import com.egybank.helpers.db.dao.BillsDataAccess;
import com.egybank.helpers.db.dao.TransactionDataAccess;
import com.egybank.helpers.db.dao.TransactionType;
import com.egybank.helpers.db.dao.UserDataAccess;
import com.egybank.models.Bill;
import com.egybank.models.Transaction;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;

import java.util.ArrayList;

public class BillsController extends HomeFragmentController implements BillItemView.OnBillCallbacks {
    public FlowPane billsFlowPane;

    @FXML
    public void initialize() {
        Platform.runLater( () -> root.requestFocus() );
        updateBillsFlowPane();
    }
    @Override
    protected void onBillsClicked(MouseEvent mouseEvent) {

    }

    private void updateBillsFlowPane() {
        ArrayList<Bill> bills = BillsDataAccess.getBills(Authentication.getCachedCurrentUser().getId());
        addBillsToFlowPane(bills);
    }
    private void addBillsToFlowPane(ArrayList<Bill> bills) {
        billsFlowPane.getChildren().removeAll(billsFlowPane.getChildren());

        for (Bill file: bills) {
            BillItemView itemView = new BillItemView();
            itemView.configure(file, this);
            billsFlowPane.getChildren().add(itemView);
        }
    }
    @Override
    public void onPayBill(Bill bill) {

        if (Authentication.getCurrentUser().getBalance() < bill.getAmount()) {
            AlertsHelper.showAlert(Alert.AlertType.ERROR, "Invalid Balance", "Please deposit some money you poor animal!");
            return;
        }

        BillsDataAccess.setBillPaid(bill.getId());
        UserDataAccess.withdraw(Authentication.getCurrentUser().getId(), bill.getAmount());

        Transaction withDrawTransaction = new Transaction(0,
                "Paid bill #"+bill.getId(),
                Authentication.getCurrentUser().getId(),
                -1,
                bill.getAmount(),
                TransactionType.PAYBILL.getIntRepresentation());
        TransactionDataAccess.createNewTransaction(withDrawTransaction);
        AlertsHelper.showAlert(Alert.AlertType.CONFIRMATION, "Success", "Bill paid successfully!");

        updateBillsFlowPane();
    }
}
