package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }
    public void createUsersTable() {
        try (Connection connection = Util.getConnection()) {
            try (Statement stmt = connection.createStatement()) {
                String sql = String.format(
                        "CREATE TABLE IF NOT EXISTS users(%s, %s, %s ,%s);",
                        "id INT PRIMARY KEY NOT NULL AUTO_INCREMENT",
                        "name VARCHAR(255)",
                        "lastName VARCHAR(255)",
                        "age INT"
                );
                stmt.executeUpdate(sql);
            }
        } catch (SQLException e) {
            System.out.println("Не удалось создать Table");
        }
    }

    public void dropUsersTable() {
        try (Connection connection = Util.getConnection()) {
            try (Statement stmt = connection.createStatement()) {
                String sql = "DROP TABLE IF EXISTS users";
                stmt.executeUpdate(sql);
            }
        } catch (SQLException e) {
            System.out.println("Не удалось удалить Table");
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection connection = Util.getConnection()) {
            try (PreparedStatement prStmt = connection.prepareStatement("INSERT INTO users (name, lastName, age) values(?, ?, ?)")) {
                prStmt.setString(1, name);
                prStmt.setString(2, lastName);
                prStmt.setByte(3, age);
                prStmt.executeUpdate();
                System.out.println("User с именем " + name + " добавлен в базу данных");
            }
        } catch (SQLException e) {
            System.out.println("Не удалось сохранить пользователя");
        }
    }

    public void removeUserById(long id) {
        try (Connection connection = Util.getConnection()) {
            try (PreparedStatement prStmt = connection.prepareStatement("DELETE FROM users WHERE id = (?)")) {
                prStmt.setLong(1, id);
                prStmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Не удалось сохранить пользователя");
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (PreparedStatement prStmt = Util.getConnection().prepareStatement("SELECT name, lastName, age FROM users;")){
            try (ResultSet rs = prStmt.executeQuery()) {
                while (rs.next()) {
                    users.add(makeUser(rs));
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }
    private User makeUser(ResultSet rs) throws SQLException {
        return new User(rs.getString(1),
                rs.getString(2),
                rs.getByte(3));
    }

    public void cleanUsersTable() {
        try (Connection connection = Util.getConnection()) {
            try (Statement stmt = connection.createStatement()){
                String sql = "TRUNCATE TABLE users";
                stmt.execute(sql);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
