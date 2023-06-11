//
//  UserDataAccess.java
//  Studyico
//
//  Created by Mohamed Salah on 4/22/2022.
//

package com.egybank.helpers.db.dao;

import com.egybank.helpers.db.DatabaseAccess;
import com.egybank.models.Item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ItemsDataAccess {
    public static ArrayList<Item> getItems() {
        ArrayList<Item> todoArrayList = new ArrayList<>();
        String query = "SELECT * FROM items";
        try (Connection connection = DatabaseAccess.connect()) {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                todoArrayList.add( new Item(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4)));
            }
        } catch (SQLException e) {
            Logger.getAnonymousLogger().log(
                    Level.SEVERE,
                    LocalDateTime.now() + ": Could not load from database ");
        }
        return todoArrayList;
    }
}
