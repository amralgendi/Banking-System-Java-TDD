package com.egybank.controllers.home.deposit;

import com.egybank.helpers.db.dao.TransactionDataAccess;
import com.egybank.helpers.db.dao.UserDataAccess;
import com.egybank.models.Transaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class DepositControllerTest {
    private DepositController depositController;

    @BeforeEach
    void setUp() {
        depositController = new DepositController();
    }

    @Test
    void depositTest() {
        int testFullUserId = 1;

        List<Transaction>  transactions = TransactionDataAccess.getTransactions(testFullUserId);


        Integer initBalance = UserDataAccess.getUser(testFullUserId).getBalance();

        // Set up test data
        int amount = 100;

        // Call the method to be tested
        depositController.deposit(testFullUserId, amount);

        Assertions.assertEquals(amount + initBalance, UserDataAccess.getUser(testFullUserId).getBalance(), "Balance not as Expected!");
        Assertions.assertEquals(transactions.size() + 1, TransactionDataAccess.getTransactions(testFullUserId).size(), "New Transaction not Added!");
    }
}