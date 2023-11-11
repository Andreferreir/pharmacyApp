package com.example.demo;

import java.util.Objects;
import java.util.Scanner;

import static com.example.demo.RegistationForm.registeredClientsQueue;

public class App {
    static User userAtMoment ;

    static void appMenu (User loggedInUser){
        boolean exit = false;
        clearScreen();

        userAtMoment = loggedInUser;
        System.out.println(loggedInUser.getClass().equals(Gestors.class));

        if ( loggedInUser.getClass().equals(Gestors.class))
        {
            while (!exit) {
                Scanner scanner = new Scanner(System.in);
                System.out.println("===== Welcome to the app " + loggedInUser.getName() + " ===== \n");
                System.out.println("1- List of users to accept ");
                System.out.println("Do you want to exit APP? (yes/no): ");
                String Choice = scanner.nextLine().toLowerCase();

                if (Choice.equals("1")){
                    showPeopleOnQeue();
                }
                else if (Choice.equals("yes")) {
                    System.out.println("===== Goodbye " + loggedInUser.getName() + " ===== \n");
                    exit = true;
                }
            }
        }
        else
        {
            while (!exit) {
                Scanner scanner = new Scanner(System.in);
                System.out.print("Do you want to exit APP? (yes/no): ");
                String Choice = scanner.nextLine().toLowerCase();

                if (Choice.equals("yes")) {
                    System.out.println("===== Goodbye " + loggedInUser.getName() + " ===== \n");
                    exit = true;
                }
            }
        }
    }

    private static void showPeopleOnQeue() {
        for (User user : registeredClientsQueue) {
            System.out.println(user.getName());
        }

        System.out.println("Want to accept the frist one?");
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine().toLowerCase();

        if (choice.equals("yes")){

            User retrievedObject = Objects.requireNonNull(registeredClientsQueue.poll());

            switch (retrievedObject.getUserType().toString()) {
                case "CLIENT" -> AddNewUserToFile.addJsonObjectTofile(JsonConvertible.objectToJson(retrievedObject), UserType.CLIENT);
                case "PHARMACIST" -> AddNewUserToFile.addJsonObjectTofile(JsonConvertible.objectToJson(retrievedObject), UserType.PHARMACIST);
                default -> throw new IllegalArgumentException("Unhandled case: " + retrievedObject.getUserType().toString());
            }
        }
        else if(choice.equals("no")){
            Objects.requireNonNull(registeredClientsQueue.poll());
            System.out.println("Removed with success");
        }
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n");
    }

}
