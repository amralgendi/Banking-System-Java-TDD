package com.egybank.controllers.home.buy;

import com.egybank.controllers.home.HomeFragmentController;
import com.egybank.helpers.AlertsHelper;
import com.egybank.helpers.auth.Authentication;
import com.egybank.helpers.db.dao.ItemsDataAccess;
import com.egybank.helpers.db.dao.TransactionDataAccess;
import com.egybank.helpers.db.dao.TransactionType;
import com.egybank.helpers.db.dao.UserDataAccess;
import com.egybank.models.Item;
import com.egybank.models.Transaction;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;

import java.util.ArrayList;

public class BuyController extends HomeFragmentController implements BuyItemView.OnItemCallbacks {
    public FlowPane itemsFlowPane;

    @FXML
    public void initialize() {
        Platform.runLater( () -> root.requestFocus() );
        updateItemsFlowPane();
    }
    @Override
    protected void onBuyClicked(MouseEvent mouseEvent) {

    }

    private void updateItemsFlowPane() {
        ArrayList<Item> items = ItemsDataAccess.getItems();
        addItemsToFlowPane(items);
    }
    private void addItemsToFlowPane(ArrayList<Item> items) {
        itemsFlowPane.getChildren().removeAll(itemsFlowPane.getChildren());

        for (Item file: items) {
            BuyItemView itemView = new BuyItemView();
            itemView.configure(file, this);
            itemsFlowPane.getChildren().add(itemView);
        }
    }

    @Override
    public void onBuyItem(Item item) {

        if (Authentication.getCurrentUser().getBalance() < item.getPrice()) {
            AlertsHelper.showAlert(Alert.AlertType.ERROR, "Invalid Balance", "Please deposit some money you poor animal!");
            return;
        }

        UserDataAccess.withdraw(Authentication.getCurrentUser().getId(), item.getPrice());

        Transaction withDrawTransaction = new Transaction(0,
                "Bought "+item.getName(),
                Authentication.getCurrentUser().getId(),
                -1,
                item.getPrice(),
                TransactionType.BUYITEM.getIntRepresentation());
        TransactionDataAccess.createNewTransaction(withDrawTransaction);
        AlertsHelper.showAlert(Alert.AlertType.CONFIRMATION, "Success", "Item bought successfully!");
    }
}
