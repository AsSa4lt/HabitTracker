package cz.cuni.mff.java.controllers;

import cz.cuni.mff.java.trying.your.luck.Roulette;
import cz.cuni.mff.java.helpers.Constants;
import cz.cuni.mff.java.helpers.FileManager;
import cz.cuni.mff.java.helpers.Printer;
import cz.cuni.mff.java.helpers.UserManager;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * Main class that provides functions to run program
 */
public class ProgramController {
    /**
     * Enum to specify a program state right now
     */
    public enum UIState {
        Logging,
        MainPage,
        HabitsList,
        InsideHabit,
        HabitCreation,
        Exit,
        FilteringHabits,
        RouletteForHabits
    }

    /**
     * Name of a current User
     */
    public static String User = "Guest";

    /**
     * Current state of the program, we always start with logging
     */
    public static UIState ProgramState = UIState.Logging;

    /**
     * Start of the program, function that provides logging
     */
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

    /**
     * Main program loop
     * First we always print current menu
     * And only then we read input from the user in some static func in controller
     */
    public static void Run(){
        try {
            HabitsController.ReadHabits();
        }catch (IOException e){
            System.out.println("Error reading habits for user: " + User);
        }
        while (true) {
            PrintProgram();

            ReadInputFromController();
        }
    }

    /**
     * Function that prints what you can do right now
     * All calls are dont in Printer
     */
    private static void PrintProgram(){
        switch (ProgramState) {
            case UIState.Logging -> SelectUser();
            case UIState.MainPage -> Printer.PrintMainMenu();
            case UIState.HabitsList -> Printer.PrintListHabitsWrapperForMenuController(HabitsController.Habits);
            case UIState.Exit -> System.exit(0);
            case UIState.InsideHabit -> Printer.PrintHabitDescription(HabitsController.SelectedHabit);
            case UIState.FilteringHabits -> Printer.PrintFilterMenu();
            case UIState.RouletteForHabits -> Printer.PrintRouletteRules();
            default -> System.out.print("\033[H\033[2J");
        }
    }

    /**
     * For each possible programState
     * It calls a controller that need to read user input
     * And provides a new state for a program
     */
    private static void ReadInputFromController(){
        switch (ProgramState){
            case UIState.MainPage -> ProgramState = MainMenuController.ReadUserInput();
            case UIState.HabitCreation -> {
                HabitsController.CreateNewHabitUser();
                ProgramState = UIState.MainPage;
            }
            case UIState.HabitsList -> { ProgramState = HabitsListController.ReadUserInputHabits();}
            case UIState.InsideHabit -> { ProgramState = HabitsListController.ReadUserInputInsideHabit(); }
            case UIState.FilteringHabits -> { ProgramState = FilterController.ReadUserInput(); }
            case UIState.RouletteForHabits -> { ProgramState = Roulette.PlayRoulette();}
        }
    }

    /**
     * Deleted all guest files
     * Guest files shouldn't be left after exiting the program
     */
    public static void ClearGuest(){
        // look for folder "Guest", if found - delete everything in a folder, if no folder - create it
        File guestFolder = new File(Constants.UsersDirectory + "Guest");
        FileManager.DeleteFolder(guestFolder);
    }
}
