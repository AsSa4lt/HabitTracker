package cz.cuni.mff.java.controllers;

import cz.cuni.mff.java.helpers.Constants;
import cz.cuni.mff.java.helpers.FileManager;

import java.io.File;
import java.util.Scanner;

public class ProgramController {
    static public String User = "Guest";
    public static void SelectUser(){
        ClearGuest();
        System.out.println("Enter your name");
        // read user
        do {
            Scanner scanner = new Scanner(System.in);
            String userName = scanner.nextLine();

            // check if folder exists
            File userFile = new File(Constants.UsersDirectory + userName);
            if(userFile.exists()){
                User = userName;
                break;
            }

            System.out.println("No user found, do you want to create a new user? [y/n]");
            String answer = scanner.nextLine();
            if(answer.equalsIgnoreCase("y")){
                User = userName;
            }

            // if we are here, so user needs to try again
            System.out.println("Try again");
        }while (true);
        System.out.print("\033[H\033[2J");
        System.out.flush();

        System.out.println("Welcome, " + User + "!");
    }

    private static void ClearGuest(){
        // look for folder "Guest", if found - delete everything in a folder, if no folder - create it
        File guestFolder = new File(Constants.UsersDirectory + "Guest");
        FileManager.DeleteFolder(guestFolder);
    }
}
