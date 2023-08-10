package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();

        userService.saveUser("John", "Johnson", (byte)23);
        userService.saveUser("Olga", "Petrova", (byte)34);
        userService.saveUser("Steave", "Jobs", (byte)48);
        userService.saveUser("Lev", "Bushinskiy", (byte)56);

        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();

    }
}
