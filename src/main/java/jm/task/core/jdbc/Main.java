package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class Main {


    public static void main(String[] args) {
        UserServiceImpl service = new UserServiceImpl();

        service.createUsersTable();

        service.saveUser("Vova", "Ivanov", (byte) 15);
        service.saveUser("Сережа", "Петров", (byte) 16);
        service.saveUser("Коля", "Кузнецов", (byte) 17);
        service.saveUser("Паша", "Сидоров", (byte) 18);

 //       service.removeUserById(1);

        service.getAllUsers();

        service.cleanUsersTable();

       service.dropUsersTable();

    }
}

