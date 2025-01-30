package cz.cuni.mff.java.controllers;

import java.util.Scanner;

public class HabitsListController {
    public static ProgramController.UIState ReadUserInputHabits(){
        Scanner scanner = new Scanner(System.in);
        String operation = scanner.nextLine();

        return ProgramController.UIState.Exit;
    }
}
