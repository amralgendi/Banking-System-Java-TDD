package com.egybank;

import com.egybank.helpers.db.DatabaseAccess;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class EgyBankApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        DatabaseAccess.initSQLite();

        Parent root = FXMLLoader.load(getClass().getResource("/layout/welcome_view.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.getIcons().add(new Image(getClass().getResource("/imgs/app-icon.png").toString()));
        stage.setTitle("Bank of Egypt");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
//alawi
    public static void main(String[] args) {
        launch();
    }
}