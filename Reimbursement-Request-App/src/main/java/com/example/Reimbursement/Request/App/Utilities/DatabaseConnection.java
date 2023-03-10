package com.example.Reimbursement.Request.App.Utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    public static void main(String[] args) {
        System.out.println(createConnection());
    }

    public static Connection createConnection() {
        try {
            return DriverManager.getConnection(String.format(
                    "jdbc:mysql://localhost:3306/reimbursement_request_app?user=%s&password=%s",
                    System.getenv("Username"),
                    System.getenv("Password")
                )
            );
        } catch (SQLException exception) {
            exception.printStackTrace();
            return null;
        }
    }
}
