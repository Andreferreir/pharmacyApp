package com.example.demo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class AddNewUserToFile {

    public AddNewUserToFile(){}

    static boolean addJsonObjectTofile(String jsonObject, UserType type){
        String fileName = "src/main/resources/" + type + ".txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            if (new File(fileName).length() > 0) {
                writer.newLine(); // Add a newline if the file is not empty
            }
            writer.append(jsonObject);
            System.out.println("JSON appended to file: " + fileName);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
