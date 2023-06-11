module com.example.egyptBank {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires java.sql;
    requires org.xerial.sqlitejdbc;

    opens com.egybank to javafx.fxml;
    exports com.egybank;
    exports com.egybank.controllers.onboarding.login;
    exports com.egybank.controllers.onboarding.signup;
    exports com.egybank.controllers.onboarding.welcome;
    exports com.egybank.controllers.home.deposit;
    exports com.egybank.controllers.home.withdraw;
    exports com.egybank.controllers.home.transfer;
    exports com.egybank.controllers.home.bills;
    exports com.egybank.controllers.home.buy;
    exports com.egybank.controllers.home.account;
    exports com.egybank.controllers.home;

    opens com.egybank.controllers.onboarding.login to javafx.fxml;
    opens com.egybank.controllers.onboarding.signup to javafx.fxml;
    opens com.egybank.controllers.onboarding.welcome to javafx.fxml;
    opens com.egybank.controllers.home.deposit to javafx.fxml;
    opens com.egybank.controllers.home.withdraw to javafx.fxml;
    opens com.egybank.controllers.home.transfer to javafx.fxml;
    opens com.egybank.controllers.home.bills to javafx.fxml;
    opens com.egybank.controllers.home.buy to javafx.fxml;
    opens com.egybank.controllers.home.account to javafx.fxml;
    opens com.egybank.controllers.home to javafx.fxml;
    exports com.egybank.controllers;
    opens com.egybank.controllers to javafx.fxml;
}