package com.egybank.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.EventObject;

public class SceneController {

    private static void showScene(Parent parent, EventObject eventObject) {
        Stage stage = (Stage) ((Node)eventObject.getSource()).getScene().getWindow();
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
    }

    public static void showSignupScene(EventObject eventObject) {
        try {
            Parent root = FXMLLoader.load(SceneController.class.getResource("/layout/signup_view.fxml"));
            showScene(root, eventObject);
        }catch (IOException e) { }
    }
    public static void showLoginScene(EventObject eventObject) {
        try {
            Parent root = FXMLLoader.load(SceneController.class.getResource("/layout/login_view.fxml"));
            showScene(root, eventObject);
        }catch (IOException e) { }
    }

    public static void showDepositScene(EventObject eventObject) {
        try {
            Parent root = FXMLLoader.load(SceneController.class.getResource("/layout/deposit_view.fxml"));
            showScene(root, eventObject);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static void showWithdrawScene(EventObject eventObject) {
        try {
            Parent root = FXMLLoader.load(SceneController.class.getResource("/layout/withdraw_view.fxml"));
            showScene(root, eventObject);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static void showAcountScene(EventObject eventObject) {
        try {
            Parent root = FXMLLoader.load(SceneController.class.getResource("/layout/account_view.fxml"));
            showScene(root, eventObject);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static void showBuyScene(EventObject eventObject) {
        try {
            Parent root = FXMLLoader.load(SceneController.class.getResource("/layout/buy_view.fxml"));
            showScene(root, eventObject);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static void showBillsScene(EventObject eventObject) {
        try {
            Parent root = FXMLLoader.load(SceneController.class.getResource("/layout/bills_view.fxml"));
            showScene(root, eventObject);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static void showTransferScene(EventObject eventObject) {
        try {
            Parent root = FXMLLoader.load(SceneController.class.getResource("/layout/transfer_view.fxml"));
            showScene(root, eventObject);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
