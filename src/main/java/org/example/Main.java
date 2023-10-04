package org.example;

import org.example.client.Client;
import org.example.client.create.Register;
import org.example.client.read.Read;

import java.time.OffsetDateTime;
import java.util.Scanner;
import java.util.regex.*;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("Welcome! What do you want?\n");
        System.out.println("1. Create account");
        System.out.println("2. View account");
        System.out.println("3. Edit account");
        System.out.println("4. Delete account");
        System.out.println("\nor enter another key for exit");

        String response = scan.next();

        switch (response) {
            case "1":
                System.out.println("What is your name?");
                String name = scan.next();

                String regex = "^[A-Za-z]{0,10}$";

                Pattern pattern = Pattern.compile(regex);

                Matcher matcher = pattern.matcher(name);

                if (!matcher.matches()) {
                    System.out.println("The name must contain only letters and the size must be up to 10 characters\n");
                    System.out.println("Insert you name again:");
                    name = scan.next();
                }

                OffsetDateTime since = OffsetDateTime.now();

                scan.nextLine();

                System.out.println("Create a password:");
                String password = scan.nextLine();

                Client newClient = new Client(null, name, since, password);

                Register.register(newClient);

                break;
            case "2":
                System.out.println("What is your account name?");
                String clientAccountName = scan.next();

                scan.nextLine();

                System.out.println("What is your account password?");
                String clientAccountPassword = scan.nextLine();

                Read.read(clientAccountName, clientAccountPassword);
                break;
            case "3":
                System.out.println("In progress...");
                break;
            case "4":
                System.out.println("In progress...");
                break;
            default:
                System.out.println("Exit...");
                System.exit(0);
        }
    }
}