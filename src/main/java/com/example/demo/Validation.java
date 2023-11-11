package com.example.demo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Validation {
    Validation(){}
    static final int minimumPassSize = 5;

    private static String getUsernameSubstring(String input) {
        int index = input.indexOf(":");
        if (index != -1 && index < input.length() - 1) {
            return input.substring(index + 1);
        } else {
            // Handle the case where ":" is not found or is at the end of the string
            return null; // or throw an exception, return a default value, etc.
        }
    }

    public static User isValidLogin(String username, String password, UserType userType){
        String filePath = "src/main/resources/" + userType.toString() + ".txt";

        String[] userData = findUserByUsername(filePath, username);

        if (userData != null) {
            /*System.out.println("User with username '" + username + "' found:");
            System.out.println("Username: " + getUsernameSubstring(userData[0]));
            System.out.println("Password: " + userData[1]);
            System.out.println("Email: " + userData[2]);
            System.out.println("Name: " + userData[3]);
            System.out.println("State: " + userData[4]);
            System.out.println("User Type: " + userData[5]*/

            if (userType == UserType.CLIENT) {
                return new Clients(getUsernameSubstring(userData[0]),getUsernameSubstring(userData[1]),getUsernameSubstring(userData[2]),getUsernameSubstring(userData[3]),getUsernameSubstring(userData[4]), UserType.CLIENT,Long.parseLong(userData[6]),Long.parseLong(userData[7]));
            }
            else if (userType == UserType.PHARMACIST) {
                return new Pharmacists(getUsernameSubstring(userData[0]),getUsernameSubstring(userData[1]),getUsernameSubstring(userData[2]),getUsernameSubstring(userData[3]),getUsernameSubstring(userData[4]), UserType.CLIENT,Long.parseLong(userData[6]),Long.parseLong(userData[7]));
            }
            else if (userType == UserType.GESTOR) {
                return new Gestors(getUsernameSubstring(userData[0]),getUsernameSubstring(userData[1]),getUsernameSubstring(userData[2]),getUsernameSubstring(userData[3]),getUsernameSubstring(userData[4]), UserType.CLIENT);
            }
        } else {
            System.out.println("User with username '" + username + "' not found.");
        }
        return null;
    }

    private static String[] findUserByUsername(String filePath, String targetUsername) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Assuming the format of each line is a JSON-like structure
                if (line.contains("\"username\":\"" + targetUsername + "\"")) {
                    // Extract fields into an array
                    String[] userData = line.trim().replaceAll("[{}\"]", "").split(",");
                    return userData;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Return null if the username is not found in any line
        return null;
    }

    public static boolean isValidRegistration(String username, String password, String email,String name, String state,UserType type) {
        /* TODO:::: Add check if email or login is being used */

        return password.length() >= minimumPassSize && email != null;
    }
}
