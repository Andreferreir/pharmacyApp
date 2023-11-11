package com.example.demo;

import javax.swing.*;
import java.util.*;

public class RegistationForm {

    static final Queue<User> registeredClientsQueue = new LinkedList<>();

    public static void menu() {
        Scanner scanner = new Scanner(System.in);
        long nif = 0L;
        long tlf = 0L;
        String username;
        String password;
        String email;
        String name;
        String state;
        UserType type;
        boolean exit = false;

        while (!exit) {
            System.out.println("===== Registration Form =====");

            System.out.print("Enter username: ");
            username = scanner.nextLine();

            System.out.print("Enter password: ");
            password = scanner.nextLine();

            System.out.print("Enter email: ");
            email = scanner.nextLine();

            System.out.print("Enter name: ");
            name = scanner.nextLine();

            System.out.print("Enter estado (active/inactive): ");
            state = scanner.nextLine();

            System.out.print("Enter tipo (GESTOR/PHARMACIST/CLIENT): ");
            String typeString = scanner.nextLine();
            type = UserType.valueOf(typeString.toUpperCase());

            if (type.toString().equals("PHARMACIST") || type.toString().equals("CLIENT")){
                System.out.print("Enter NIF: ");
                nif = Long.parseLong(scanner.nextLine());

                System.out.print("Enter telefone: ");
                tlf = Long.parseLong(scanner.nextLine());
            }

            // Handle registration logic here
            boolean isValidRegistration = Validation.isValidRegistration(username, password, email, name, state, type);
            User newClient ;
            if (isValidRegistration) {
                switch (type) {
                    case CLIENT:
                        newClient = new Clients(username, password, email, name, state, type, nif, tlf);
                        // Store the new client object in the list
                        registeredClientsQueue.add(newClient);
                        break;
                    case PHARMACIST:
                        newClient = new Pharmacists(username, password, email, name, state, type, nif, tlf);

                        registeredClientsQueue.add(newClient);

                        break;

                    case GESTOR:
                        newClient = new Gestors(username, password, email, name, state, type);

                        registeredClientsQueue.add(newClient);

                        break;
                    default:
                        throw new IllegalArgumentException("Unhandled case: " + type);
                }

                System.out.print("Waiting registration approval");
                LoginForm.main(null);

            } else {
                JOptionPane.showInputDialog(null, "Please fill correctly", "Unsuccessful");
            }
        }

        scanner.close();
    }
}

