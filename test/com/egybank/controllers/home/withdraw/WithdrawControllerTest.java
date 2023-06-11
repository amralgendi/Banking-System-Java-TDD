package com.egybank.controllers.home.withdraw;

import com.egybank.helpers.db.dao.TransactionDataAccess;
import com.egybank.helpers.db.dao.UserDataAccess;
import com.egybank.models.Transaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WithdrawControllerTest {
    private WithdrawController withdrawController;
    @BeforeEach
    void setUp() {
        withdrawController = new WithdrawController();
    }

    @Test
    void withdrawTest() {
        int testFullUserId = 1;

        List<Transaction> transactions = TransactionDataAccess.getTransactions(testFullUserId);


        Integer initBalance = UserDataAccess.getUser(testFullUserId).getBalance();

        // Set up test data
        int amount = 100;

        // Call the method to be tested
        withdrawController.withdraw(testFullUserId, amount);

        Assertions.assertEquals(initBalance - amount, UserDataAccess.getUser(testFullUserId).getBalance(), "Balance not as Expected!");
        Assertions.assertEquals(transactions.size() + 1, TransactionDataAccess.getTransactions(testFullUserId).size(), "New Transaction not Added!");
    }

    @Test
    void withdrawTestFail() {
        int testEmptyUserId = 2;

        Integer initBalance = UserDataAccess.getUser(testEmptyUserId).getBalance();

        // Set up test data
        int amount = 100000;

        // Call the method to be tested

        IllegalArgumentException exception = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> withdrawController.withdraw(testEmptyUserId, amount)
        );

        Assertions.assertEquals("Insufficient Balance", exception.getMessage());
    }
}