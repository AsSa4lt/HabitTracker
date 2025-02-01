package cz.cuni.mff.java.controllers;

import java.io.IOException;
import java.util.Scanner;

/**
 * Class to provide reading input for MainMenu
 * @author Rostyslav Liapkin
 */
public class MainMenuController {
    /**
     * Reads input and decides what is the next state of the program
     * @return Returns next state of the program
     */
    public static ProgramController.UIState ReadUserInput(){
        System.out.println("Enter your input");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        switch (input){
            case "1" -> {
                return ProgramController.UIState.HabitsList;
            }
            case "2" -> {
                return ProgramController.UIState.HabitCreation;
            }
            case "3" -> {
                return ProgramController.UIState.Logging;
            }
            case "4" -> {
                System.out.println("Saving data...");
                try {
                    HabitsController.SaveHabits();
                }catch (IOException e){
                    System.out.println("Something went wrong");
                }
                return ProgramController.UIState.MainPage;
            }
            case "5" -> {
                ProgramController.ClearGuest();
                return ProgramController.UIState.Exit;
            }
            default -> {
                System.out.println("Invalid input");
                return ProgramController.UIState.MainPage;
            }
        }
    }

}
