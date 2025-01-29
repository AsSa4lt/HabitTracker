package cz.cuni.mff.java.helpers;

import java.io.File;
import java.io.IOException;

public class UserManager {
    public static void CreateNewUser(String Name){
        try {
            // First we need to create a folder
            FileManager.CreateFolder(new File(Constants.UsersDirectory + "/" + Name));
            // then we need to create an empty habits.csv file
            File file = new File(Constants.UsersDirectory + "/" + Name + "/habits.csv");
            // if file exists in a folder that has been created now, something went REALLY wrong
            if(!file.createNewFile())
                throw new IOException();
        }catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            System.out.println("Critical error during User creation, all next steps can be compromised");
        }
    }
}
