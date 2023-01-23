package jm.task.core.jdbc;


import jm.task.core.jdbc.dao.UserDaoHibernateImpl;

public class Main {
    public static void main(String[] args) {
        UserDaoHibernateImpl userDaoHibernate = new UserDaoHibernateImpl();
        userDaoHibernate.createUsersTable();
        userDaoHibernate.saveUser("Tom",	"Smith", (byte) 22);
        userDaoHibernate.saveUser("Bob",	"Johnson", (byte) 17);
        userDaoHibernate.saveUser("Joe",	"Jones", (byte) 17);
        userDaoHibernate.saveUser("Peter",	"Davis", (byte) 16);
        System.out.println(userDaoHibernate.getAllUsers().toString());
        userDaoHibernate.cleanUsersTable();
        userDaoHibernate.dropUsersTable();
    }
}
