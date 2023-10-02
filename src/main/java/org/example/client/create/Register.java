package org.example.client.create;

import io.github.cdimascio.dotenv.Dotenv;
import org.example.client.Client;
import org.example.client.PasswordUtilities;

import java.sql.*;
import java.util.Objects;

public class Register implements PasswordUtilities {
    public void register(Client newClient) {
        Connection connection;
        PreparedStatement preparedStatement;

        String clientID = newClient.getID();
        String clientName = newClient.getName();
        int clientAge = newClient.getAge();
        String clientPassword = hash(newClient.getPassword());

        try {
            Class.forName("org.sqlite.JDBC");

            Dotenv dotenv = Dotenv.load();

            final String DB_CLIENTS_PATH = Objects.requireNonNull(dotenv.get("DB_CLIENTS_PATH"));

            connection = DriverManager.getConnection(DB_CLIENTS_PATH);

            String sql = "insert into clients values (?, ?, ?, ?)";

            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, clientID);
            preparedStatement.setString(2, clientName);
            preparedStatement.setInt(3, clientAge);
            preparedStatement.setString(4, clientPassword);

            preparedStatement.executeUpdate();

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
