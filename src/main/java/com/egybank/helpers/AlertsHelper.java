//
//  AlertsHelper.java
//  Studyico
//
//  Created by Mohamed Salah on 4/23/2022.
//

package com.egybank.helpers;

import javafx.scene.control.Alert;

public class AlertsHelper {
    public static void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setContentText(content);
        alert.setTitle(title);
        alert.show();
    }
}
