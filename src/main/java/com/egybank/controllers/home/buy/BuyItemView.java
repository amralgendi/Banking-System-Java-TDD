//
//  FileItemView.java
//  Studyico
//
//  Created by Mohamed Salah on 4/30/2022.
//

package com.egybank.controllers.home.buy;

import com.egybank.models.Item;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BuyItemView extends Pane implements Initializable {

    interface OnItemCallbacks {
        void onBuyItem(Item item);
    }
    @FXML
    private Label itemNameLabel, itemCategoryLabel, itemPriceLabel;

    @FXML
    private ImageView buyBtn;
    private OnItemCallbacks onItemCallbacks;

    public BuyItemView() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/layout/buy_item_view.fxml"));
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

    private Item item;
    public void configure(Item item, OnItemCallbacks onItemCallbacks) {
        this.item = item;
        this.onItemCallbacks = onItemCallbacks;

        itemNameLabel.setText(item.getName());
        itemCategoryLabel.setText("#"+item.getId());
        itemPriceLabel.setText("$"+item.getPrice());
    }

    @FXML
    public void onBuyClicked() {
        onItemCallbacks.onBuyItem(item);
    }

}
