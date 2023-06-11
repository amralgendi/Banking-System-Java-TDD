//
//  UserDataAccess.java
//  Studyico
//
//  Created by Mohamed Salah on 4/22/2022.
//

package com.egybank.helpers.db.dao;

import com.egybank.helpers.db.DatabaseAccess;
import com.egybank.models.Transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TransactionDataAccess {

    public static Integer createNewTransaction(Transaction transaction) {
        String sql = "INSERT INTO transactions (to_account, from_account, amount, type, name) VALUES (?,?,?,?,?)";
        try (Connection connection = DatabaseAccess.connect()) {

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, transaction.getTo_account());
            statement.setInt(2, transaction.getFrom_account());
            statement.setInt(3, transaction.getAmount());
            statement.setInt(4, transaction.getType().getIntRepresentation());
            statement.setString(5, transaction.getName());
            int affectedRows = statement.executeUpdate();


            // check the affected rows
            if (affectedRows > 0) {
                // get the ID back
                try (ResultSet rs = statement.getGeneratedKeys()) {
                    if (rs.next()) {
                        return rs.getInt(1);
                    }
                } finally {
                    connection.close();
                }
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
            Logger.getAnonymousLogger().log(
                    Level.SEVERE,
                    LocalDateTime.now() + ": Could not add to database");
        }

        return null;
    }

    public static ArrayList<Transaction> getTransactions(int accountId) {
        ArrayList<Transaction> todoArrayList = new ArrayList<>();
        String query = "SELECT * FROM transactions WHERE from_account=? OR to_account=?";
        try (Connection connection = DatabaseAccess.connect()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, accountId);
            statement.setInt(2, accountId);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                todoArrayList.add( new Transaction(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getInt(4),
                        rs.getInt(5),
                        rs.getInt(6)));
            }
        } catch (SQLException e) {
            Logger.getAnonymousLogger().log(
                    Level.SEVERE,
                    LocalDateTime.now() + ": Could not load from database ");
        }
        return todoArrayList;
    }
}
