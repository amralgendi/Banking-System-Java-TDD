//
//  UserDataAccess.java
//  Studyico
//
//  Created by Mohamed Salah on 4/22/2022.
//

package com.egybank.helpers.db.dao;

import com.egybank.helpers.db.DatabaseAccess;
import com.egybank.models.Bill;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BillsDataAccess {
    public static ArrayList<Bill> getBills(int accountId) {
        ArrayList<Bill> todoArrayList = new ArrayList<>();
        String query = "SELECT * FROM bills WHERE account_id=?";
        try (Connection connection = DatabaseAccess.connect()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, accountId);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                todoArrayList.add( new Bill(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getInt(4),
                        rs.getInt(5)));
            }
        } catch (SQLException e) {
            Logger.getAnonymousLogger().log(
                    Level.SEVERE,
                    LocalDateTime.now() + ": Could not load from database ");
        }
        return todoArrayList;
    }
    public static Integer setBillPaid(int billId) {
        String sql = "UPDATE bills set paid=1 WHERE id=?";
        try (Connection connection = DatabaseAccess.connect()) {

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, billId);
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
                    LocalDateTime.now() + ": Could not update database");
        }

        return null;
    }
}
