//
//  FileItemView.java
//  Studyico
//
//  Created by Mohamed Salah on 4/30/2022.
//

package com.egybank.controllers.home.bills;

import com.egybank.models.Bill;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BillItemView extends Pane implements Initializable {

    interface OnBillCallbacks {
        void onPayBill(Bill bill);
    }
    @FXML
    private Label billNameLabel, billNumberLabel, billAmountLabel;

    @FXML
    private ImageView payBtn;
    private OnBillCallbacks onBillCallbacks;

    public BillItemView() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/layout/bill_item_view.fxml"));
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

    private Bill bill;
    public void configure(Bill bill, OnBillCallbacks onBillCallbacks) {
        this.bill = bill;
        this.onBillCallbacks = onBillCallbacks;

        billNameLabel.setText((bill.isPaid() ? "[PAID]" : "")+bill.getName());
        billNumberLabel.setText("#"+bill.getId());
        billAmountLabel.setText("$"+bill.getAmount());

        payBtn.setVisible(!bill.isPaid());
    }

    @FXML
    public void onPayClicked() {
        onBillCallbacks.onPayBill(bill);
    }

}
