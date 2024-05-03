package jm.task.core.jdbc.util;
import java.sql.*;

public class Util {
    private static final String url = "jdbc:postgresql://localhost:5432/task1";
    private static final String login = "postgres";
    private static final String password = "Artem123";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, login, password);
    }

    // реализуйте настройку соеденения с БД
}
