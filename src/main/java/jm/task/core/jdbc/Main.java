package jm.task.core.jdbc;
import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        try (Connection connection = Util.getConnection()
             //Statement stat = connection.createStatement()
        )
        {
            UserDao userDao = new UserDaoJDBCImpl(connection);
            UserService userService = new UserServiceImpl(userDao);

            userService.createUsersTable();

            User user1 = new User("Иван", "Иванов", (byte) 30);
            User user2 = new User("Петр", "Иванов", (byte) 12);
            User user3 = new User("Сергей", "Васильев", (byte) 42);
            User user4 = new User("Василий", "Сергеев", (byte) 5);

            userService.removeUserById(1L);
            System.out.println("Пользователь с ID 1 успешно удален.");

             List<User> users = userService.getAllUsers();
            for (User user : users) {
                System.out.println(user);
            }
/*
            userService.cleanUsersTable();
            System.out.println("Таблица пользователей успешно очищена.");

            userService.dropUsersTable();
            System.out.println("Таблица пользователей успешно удалена.");
*/



            // реализуйте алгоритм здесь
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
