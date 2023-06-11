//
//  UserDataAccess.java
//  Studyico
//
//  Created by Mohamed Salah on 4/22/2022.
//

package com.egybank.helpers.db.dao;

import com.egybank.helpers.db.DatabaseAccess;
import com.egybank.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDataAccess {
    public static Integer createNewUser(User user) {
        String sql = "INSERT INTO users (name, email, password, balance) VALUES (?,?,?, ?)";
        try (Connection connection = DatabaseAccess.connect()) {

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.setInt(4, user.getBalance());
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
            Logger.getAnonymousLogger().log(
                    Level.SEVERE,
                    LocalDateTime.now() + ": Could not add to database");
        }

        return null;
    }

    public static Integer deleteUser(Integer id) {
        String sql = "DELETE FROM users WHERE id=?";
        try (Connection connection = DatabaseAccess.connect()) {

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
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

    public static User getUser(int id) {
        String query = "SELECT * FROM users WHERE id=?";
        try (Connection connection = DatabaseAccess.connect()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return new User(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getInt(5));
            }
        } catch (SQLException e) {
            Logger.getAnonymousLogger().log(
                    Level.SEVERE,
                    LocalDateTime.now() + ": Could not load from database ");
        }
        return null;
    }

    public static User getUser(String email, String password) {
        String query = "SELECT * FROM users WHERE email=? AND password=?";
        try (Connection connection = DatabaseAccess.connect()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return new User(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getInt(5));
            }
        } catch (SQLException e) {
            Logger.getAnonymousLogger().log(
                    Level.SEVERE,
                    LocalDateTime.now() + ": Could not load from database ");
        }
        return null;
    }

    public static boolean isUserAvailable(String email) {
        String query = "SELECT * FROM users WHERE email=?";
        try (Connection connection = DatabaseAccess.connect()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, email);
            ResultSet rs = statement.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            Logger.getAnonymousLogger().log(
                    Level.SEVERE,
                    LocalDateTime.now() + ": Could not load from database ");
        }
        return false;
    }

    public static Integer deposit(int userId, int amount) {
        String sql = "UPDATE users set balance=balance+? WHERE id=?";
        try (Connection connection = DatabaseAccess.connect()) {

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, amount);
            statement.setInt(2, userId);
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
    public static Integer withdraw(int userId, int amount) {
        String sql = "UPDATE users set balance=balance-? WHERE id=?";
        try (Connection connection = DatabaseAccess.connect()) {

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, amount);
            statement.setInt(2, userId);
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

    public static Integer transfer(int fromUserId, int toUserId, int amount) {
        withdraw(fromUserId, amount);
        deposit(toUserId, amount);
        return 1;
    }
}
