package com.egybank.controllers.home.transfer;

import com.egybank.helpers.db.dao.UserDataAccess;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TransferControllerTest {
    private TransferController transferController;
    @BeforeEach
    void setUp() {
        transferController = new TransferController();
    }

    @Test
    void transfervalid() {

        Integer balance1 = UserDataAccess.getUser(1).getBalance();
        Integer balance2 = UserDataAccess.getUser(2).getBalance();
        transferController.transfer(1, 2, 100);

        Assertions.assertEquals(balance1 - 100, UserDataAccess.getUser(1).getBalance(), "Balance not as Expected!");
        Assertions.assertEquals(balance2 + 100, UserDataAccess.getUser(2).getBalance(), "Balance not as Expected!");
    }
    @Test
    void transferinsufficient() {
        Integer balance1 = UserDataAccess.getUser(1).getBalance();
        Integer balance2 = UserDataAccess.getUser(2).getBalance();
        IllegalArgumentException exception = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> transferController.transfer(1, 2, 100000)
        );
        Assertions.assertEquals("Insufficient Balance", exception.getMessage());
    }

    @Test
    void transferInvalidAccount(){
        IllegalArgumentException exception = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> transferController.transfer(1, 8, 100)
        );
        Assertions.assertEquals("Invalid Account Id", exception.getMessage());
    }
}