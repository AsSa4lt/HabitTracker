package cz.cuni.mff.java.controllers;

import cz.cuni.mff.java.helpers.Constants;
import cz.cuni.mff.java.helpers.FileManager;
import cz.cuni.mff.java.helpers.Printer;
import cz.cuni.mff.java.helpers.UserManager;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class ProgramController {
    public enum UIState {
        Logging,
        MainPage,
        HabitsList,
        InsideHabit,
        HabitCreation,
        Exit
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
                ProgramState = UIState.MainPage;
                break;
            }

            System.out.println("No user found, do you want to create a new user? [y/n]");
            String answer = scanner.nextLine();
            if(answer.equalsIgnoreCase("y")){
                User = userName;
                // in this case create new user
                UserManager.CreateNewUser(User);
                ProgramState = UIState.MainPage;
                break;
            }

            // if we are here, so user needs to try again
            System.out.println("Try again");
        }while (true);
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void Run(){
        try {
            HabitsController.ReadHabits();
        }catch (IOException e){
            System.out.println("Error reading habits for user: " + User);
        }
        while (true) {
            switch (ProgramState) {
                case UIState.Logging -> SelectUser();
                case UIState.MainPage -> Printer.PrintMainMenu();
                case UIState.HabitsList -> Printer.PrintHabitsList(HabitsController.Habits);
                case UIState.Exit -> System.exit(0);
                case UIState.InsideHabit -> Printer.PrintHabitDescription(HabitsController.SelectedHabit);
                default -> System.out.print("\033[H\033[2J");
            }

            switch (ProgramState){
                case UIState.MainPage -> ProgramState = MainMenuController.ReadUserInput();
                case UIState.HabitCreation -> {
                    HabitsController.CreateNewHabitUser();
                    ProgramState = UIState.MainPage;
                }
                case UIState.HabitsList -> { ProgramState = HabitsListController.ReadUserInputHabits();}
                case UIState.InsideHabit -> { ProgramState = HabitsListController.ReadUserInputInsideHabit(); }
            }
        }
    }

    private static void ClearGuest(){
        // look for folder "Guest", if found - delete everything in a folder, if no folder - create it
        File guestFolder = new File(Constants.UsersDirectory + "Guest");
        FileManager.DeleteFolder(guestFolder);
    }
}
