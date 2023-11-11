package com.example.demo;

import javax.swing.*;
import java.util.Scanner;

public class LoginForm {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        boolean exit = false;

        while (!exit) {
            System.out.println("===== Login Form ===== \n");
            System.out.print("Already have an account?: ");
            String resp = scanner.nextLine();

            if (resp.equalsIgnoreCase("N") || resp.equalsIgnoreCase("NO")) {
                RegistationForm.menu();
            }

            System.out.print("Enter username: ");
            String username = scanner.nextLine();

            System.out.print("Enter password: ");
            String password = scanner.nextLine();

            System.out.print("Enter user type (GESTOR, PHARMACIST, CLIENT): ");
            String userTypeString = scanner.nextLine();
            UserType userType;

            try {
                userType = UserType.valueOf(userTypeString);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid user type. Please enter a valid user type.");
                continue; // Skip the rest of the loop and start over
            }

            User loggedInUser  = Validation.isValidLogin(username, password, userType);
            App.appMenu(loggedInUser);
        }

        System.out.println("Program exited. Goodbye!");
        scanner.close();
    }
}
