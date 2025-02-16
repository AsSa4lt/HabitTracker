package cz.cuni.mff.java.helpers;

import java.io.File;
import java.io.IOException;

/**
 * Function that takes care of actions with users
 */
public class UserManager {
    /**
     * Function that creates necessary files for a use
     * @param Name of a user to create specific files
     */
    public static void CreateNewUser(String Name){
        try {
            // First we need to create a folder
            FileManager.CreateFolder(new File(Constants.UsersDirectory + File.separator + Name));
            // then we need to create an empty habits.csv file
            File file = new File(Constants.UsersDirectory + File.separator + Name + File.separator + Constants.HabitsFile);
            // if file exists in a folder that has been created now, something went REALLY wrong
            if(!file.createNewFile())
                throw new IOException();
        }catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            System.out.println("Critical error during User creation, all next steps can be compromised");
        }
    }
}
