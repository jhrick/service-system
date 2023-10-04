package org.example.client.create;

import org.example.client.Client;
import org.example.client.HelperClass;

import java.sql.*;
import java.time.OffsetDateTime;
import java.util.UUID;

public class Register {
    public static void register(Client newClient) {
        Connection connection;
        PreparedStatement preparedStatement;

        final UUID CLIENT_ID = newClient.getUniqueId();
        final String CLIENT_NAME = newClient.getName();
        final OffsetDateTime CLIENT_SINCE = newClient.getSince();
        final String CLIENT_PASSWORD = HelperClass.toHash(newClient.getPassword());

        try {
            Class.forName("org.sqlite.JDBC");

            final String DB_CLIENTS_PATH = "jdbc:sqlite:src/main/resources/db/clients.db";

            connection = DriverManager.getConnection(DB_CLIENTS_PATH);

            String sql = "INSERT INTO `clients` VALUES (?, ?, ?, ?)";

            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, String.valueOf(CLIENT_ID));
            preparedStatement.setString(2, CLIENT_NAME);
            preparedStatement.setString(3, HelperClass.offsetDateTimeForString(CLIENT_SINCE));
            preparedStatement.setString(4, CLIENT_PASSWORD);

            preparedStatement.executeUpdate();

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
