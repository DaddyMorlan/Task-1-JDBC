package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private Connection connection;
    public UserDaoJDBCImpl(Connection connection) {
        this.connection = connection;
    }
    public UserDaoJDBCImpl(){

    }
@Override
    public void createUsersTable() {
    String create = "CREATE TABLE IF NOT EXISTS users_table (id SERIAL PRIMARY KEY, name VARCHAR(50), lastName VARCHAR(50), age INT)";
    try (PreparedStatement statement = connection.prepareStatement(create)) {
        statement.executeUpdate();
        System.out.println("Таблица пользователей успешно создана.");
    } catch (SQLException e) {
    }
}
@Override
    public void dropUsersTable(){
            String sql = "DROP TABLE IF EXISTS users_table";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.executeUpdate();
                System.out.println("Таблица пользователей успешно удалена.");
            } catch (SQLException e) {
                System.err.println("Ошибка при удалении таблицы пользователей: " + e.getMessage());
            }

    }
@Override
    public void saveUser(User user) {
    if (user == null) {
        System.err.println("Ошибка: передан пустой пользователь.");
        return;
    }
            String sql = "INSERT INTO users_table (name, lastName, age) VALUES (?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, user.getName());
                statement.setString(2, user.getLastName());
                statement.setByte(3, user.getAge());
                statement.executeUpdate();
                System.out.println("Пользователь " + user.getName() + " добавлен в базу данных.");
            } catch (SQLException e) {
                System.err.println("Ошибка при добавлении пользователя: " + e.getMessage());
            }

    }

    @Override
    public void removeUserById(long id) {
    String sql = "DELETE FROM users_table WHERE id = ?";
    try (PreparedStatement statement = connection.prepareStatement(sql)) {
        statement.setLong(1, id);
        statement.executeUpdate();
        System.out.println("Пользователь с ID " + id + " успешно удален.");
    } catch (SQLException e) {
        System.err.println("Ошибка при удалении пользователя: " + e.getMessage());
    }

    }
@Override
    public List<User> getAllUsers() {
            List<User> users = new ArrayList<>();
            String sql = "SELECT * FROM users_table";
            try (PreparedStatement statement = connection.prepareStatement(sql);
                 ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Long id = resultSet.getLong("id");
                    String name = resultSet.getString("name");
                    String lastName = resultSet.getString("lastName");
                    Byte age = resultSet.getByte("age");
                    users.add(new User(id, name, lastName, age));
                }
            } catch (SQLException e) {
                System.err.println("Ошибка при получении пользователей: " + e.getMessage());
            }
            return users;
        }

@Override
    public void cleanUsersTable() {
        String sql = "TRUNCATE TABLE users_table";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.executeUpdate();
            System.out.println("Таблица пользователей успешно очищена.");
        } catch (SQLException e) {
            System.err.println("Ошибка при очистке таблицы пользователей: " + e.getMessage());
        }

    }
}
