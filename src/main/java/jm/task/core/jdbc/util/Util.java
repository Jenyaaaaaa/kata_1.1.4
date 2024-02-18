package jm.task.core.jdbc.util;


import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/test";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    public static SessionFactory buildSessionFactory() {
        try {
            Configuration configuration = new Configuration()
                    .setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect")
                    .setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver")
                    .setProperty("hibernate.connection.url", URL)
                    .setProperty("hibernate.connection.username", USERNAME)
                    .setProperty("hibernate.connection.password", PASSWORD)
                    .setProperty("hibernate.show_sql", "true")
                    .setProperty("hibernate.hbm2ddl.auto", "update")
                    .setProperty("hibernate.current_session_context_class", "thread")
                    .addAnnotatedClass(User.class);

            return configuration.buildSessionFactory();
        } catch (Exception e) {
            System.err.println("Ошибка подключения к базе данных: " + e);
        }
        return null;
    }







    Connection connection;
    public Util() {

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            if (!connection.isClosed()) {

                System.out.println("Соединение установлено");

            }

        } catch (SQLException e) {

            System.out.println("Ошибка подключения к базе данных: " + e.getMessage());
        }
    }
    public Connection getConnection (){
        return connection;
    }
}
