package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();
        userDaoJDBC.createUsersTable();
        userDaoJDBC.saveUser("Леша",	"Карпов", (byte) 22);
        userDaoJDBC.saveUser("Варя",	"Паршикова", (byte) 17);
        userDaoJDBC.saveUser("Дима",	"Кривчиков", (byte) 20);
        userDaoJDBC.saveUser("Вика",	"Березовская", (byte) 16);
        System.out.println(userDaoJDBC.getAllUsers().toString());
        userDaoJDBC.cleanUsersTable();
        userDaoJDBC.dropUsersTable();
    }
}
