package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    Util util = new Util();
    Statement statement;
    PreparedStatement preparedStatement;

    ResultSet resultSet;

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String creteTable = "CREATE TABLE IF NOT EXISTS users (id BIGINT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(255), lastName VARCHAR(255), age TINYINT)";
        try {
            statement = util.getConnection().createStatement();
            statement.execute(creteTable);
            System.out.println("Создание таблицы");
        } catch (SQLException e) {
            System.out.println("Ошибка создания таблицы" + e.getMessage());
        }
    }

    public void dropUsersTable() {
        String dropTable = "DROP TABLE IF EXISTS users";
        try {
            statement = util.getConnection().createStatement();
            statement.execute(dropTable);
            System.out.println("Удаление таблицы");
        } catch (SQLException e) {
            System.out.println("Ошибка удаления таблицы" + e.getMessage());
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String createUser = "INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)";
        try {
            preparedStatement = util.getConnection().prepareStatement(createUser);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.execute();
            System.out.println("User с именем - " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            System.out.println("Ошибка добавления пользователя" + e.getMessage());
        }
    }

    public void removeUserById(long id) {
        String removeUser = "DELETE FROM users WHERE id = ?";
        try {
            preparedStatement = util.getConnection().prepareStatement(removeUser);
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
            System.out.println("Удаление пользователя с id № " + id);
        } catch (SQLException e) {
            System.out.println("Ошибка удаления пользователя" + e.getMessage());
        }
    }

    public List<User> getAllUsers() {
        ArrayList<User> usersList = new ArrayList<User>();

        String getUsers = "SELECT * FROM users";
        try {
            preparedStatement = util.getConnection().prepareStatement(getUsers);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                usersList.add(user);
            }

            System.out.println(usersList);
        } catch (SQLException e) {
            System.out.println("Ошибка удаления пользователя" + e.getMessage());
        }
        return usersList;
    }

    public void cleanUsersTable() {
        String removeUser = "TRUNCATE TABLE users";
        try {
            statement = util.getConnection().createStatement();

            statement.execute(removeUser);
            System.out.println("Удаление всех пользователей");
        } catch (SQLException e) {
            System.out.println("Ошибка удаления пользователей" + e.getMessage());
        }
    }
}
