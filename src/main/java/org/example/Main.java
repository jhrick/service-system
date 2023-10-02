package org.example;

import org.example.client.Client;
import org.example.client.create.Register;

import javax.swing.plaf.synth.SynthTextAreaUI;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("Welcome! What do you want?");
        System.out.println("1. Create account \t3. Edit account \n2. View account  \t4. Delete account");
        System.out.println("or enter another key for exit");

        String response = scan.next();

        switch (response) {
            case "1":
                System.out.println("What is your name?");
                String name = scan.next();

                System.out.println("What is your age?");
                int age = scan.nextInt();

                scan.nextLine();

                System.out.println("Create a password:");
                String password = scan.nextLine();

                Client newClient = new Client(name, age, password);

                Register register = new Register();
                register.register(newClient);

                break;
            case "2":
                System.out.println("In progress...");
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