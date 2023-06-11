package com.egybank.helpers.db.dao;

import com.egybank.models.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class UserDataAccessTest {
    static private String name;
    static private String email;
    static private String password;
    static private Integer balance;
    static private Integer firstId;
    static private Integer deposit;
    static private Integer withdraw;

    @BeforeAll
    static public void initializeParams(){
        name = "John Doe";
        email = "amrag204@gmail.com";
        password = "testPassword@20000";
        balance = 400;
        firstId = -1;
        deposit = 50;
        withdraw = 60;
    }

    @Test
    public void userSuccess(){
        testCreateNewUser();
        testGetUserById();
        testGetUserByEmailAndPassword();
        testDeposit();
        balance += deposit;
        testGetUserById();
        testWithdraw();
        balance -= withdraw;
        testGetUserById();
    }

    @AfterEach
    public void deleteUser(){
        User user = UserDataAccess.getUser(email, password);
        UserDataAccess.deleteUser(user.getId());
    }

    private void testCreateNewUser() {
        User user = new User(0, name, email, password, balance);
        Integer userId = UserDataAccess.createNewUser(user);
        Assertions.assertNotNull(userId, "User ID should not be null");

        firstId = userId;
    }

    private void testGetUserById() {
        User user = UserDataAccess.getUser(firstId);
        Assertions.assertNotNull(user);
        Assertions.assertEquals(user.getId(), firstId, "User ID should not be null");
        Assertions.assertEquals(user.getName(), name, "User ID should not be null");
        Assertions.assertEquals(user.getEmail(), email, "User ID should not be null");
        Assertions.assertEquals(user.getPassword(), password, "User ID should not be null");
        Assertions.assertEquals(user.getBalance(), balance, "User ID should not be null");
    }

    private void testGetUserByEmailAndPassword() {
        User user = UserDataAccess.getUser(email, password);
        Assertions.assertNotNull(user);
        Assertions.assertEquals(user.getId(), firstId, "User ID should not be null");
        Assertions.assertEquals(user.getName(), name, "User ID should not be null");
        Assertions.assertEquals(user.getEmail(), email, "User ID should not be null");
        Assertions.assertEquals(user.getPassword(), password, "User ID should not be null");
        Assertions.assertEquals(user.getBalance(), balance, "User ID should not be null");
    }

    private void testIsUserAvailable() {
        boolean isAvailable = UserDataAccess.isUserAvailable(email);
        Assertions.assertTrue(isAvailable, "User should be available in the database");
    }

    private void testDeposit() {
        Integer affectedRows = UserDataAccess.deposit(firstId, deposit);
        Assertions.assertNotNull(affectedRows, "Affected rows should not be null");
    }

    private void testWithdraw() {
        Integer affectedRows = UserDataAccess.withdraw(firstId, withdraw);
        Assertions.assertNotNull(affectedRows, "Affected rows should not be null");
    }

    private void testTransfer() {
        Integer result = UserDataAccess.transfer(1, 2, 50);
        Assertions.assertEquals(1, result, "Transfer should return 1 for successful transfer");
    }
}