package cz.cuni.mff.java.controllers;

import cz.cuni.mff.java.coreClasses.Habit;
import cz.cuni.mff.java.helpers.Constants;
import cz.cuni.mff.java.helpers.FileManager;
import cz.cuni.mff.java.helpers.Printer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProgramController {
    public enum UIState {
        Logging,
        MainPage,
        HabitsList,
        InsideHabit
    }
    public static String User = "Guest";
    public static UIState ProgramState = UIState.Logging;
    public static HabitsController HabitsCtrl = new HabitsController();
    public static void SelectUser(){
        ProgramState = UIState.Logging;
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
    }

    public static void Run(){
        switch (ProgramState){
            case UIState.Logging -> SelectUser();
            case UIState.MainPage -> Printer.PrintMainMenu();
            case UIState.HabitsList -> Printer.PrintHabitsList(HabitsCtrl.Habits);
            default -> System.out.print("\033[H\033[2J");
        };


    }

    private static void ClearGuest(){
        // look for folder "Guest", if found - delete everything in a folder, if no folder - create it
        File guestFolder = new File(Constants.UsersDirectory + "Guest");
        FileManager.DeleteFolder(guestFolder);
    }
}
