package org.example.client.read;

import org.example.client.Client;
import org.example.client.HelperClass;
import org.example.databaseconfig.DatabaseConfig;

import java.sql.*;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class Read {
    public static void read(String name, String password) {
        Connection connection;
        Statement statement;
        ResultSet resultSet;

        final String DB_CLIENTS_PATH = DatabaseConfig.getDatabaseFile();

        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(DB_CLIENTS_PATH);
            String sql = "SELECT * FROM `clients` WHERE name = '" + name + "'";

            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            String clientName = resultSet.getString(2);

            if (clientName == null) {
                System.out.println("\nUser invalid");
                return;
            }

            String clientPassword = resultSet.getString(4);

            if (!HelperClass.checkHash(password, clientPassword)) {
                System.out.println("\nPassword invalid");
                return;
            }

            String clientId = resultSet.getString(1);

            OffsetDateTime clientSince = OffsetDateTime.parse(resultSet.getString(3));

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            Client client = new Client(UUID.fromString(clientId), clientName, clientSince, password);

            System.out.println("\nID: " + client.getUniqueId());
            System.out.println("Name: " + client.getName());
            System.out.println("Password: " + client.getPassword());

            String formattedClientSince = client.getSince().format(formatter);
            System.out.println("Since: " + formattedClientSince);

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
