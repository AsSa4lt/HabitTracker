package cz.cuni.mff.java.controllers;

import java.nio.file.InvalidPathException;
import java.time.LocalDate;
import java.util.Scanner;

/**
 * Class to control user input
 */
public class HabitsListController {
    /**
     * Checks if user wants to select some habit
     * @return next state of the program
     */
    public static ProgramController.UIState ReadUserInputHabits(){
        Scanner scanner = new Scanner(System.in);
        do {
            String operation;
            if (scanner.hasNextLine())
                operation = scanner.nextLine();
            else continue;

            if (operation.equals("exit")){
                System.exit(0);
            }else if(operation.equals("menu") || operation.equals("main page")){
                return ProgramController.UIState.MainPage;
            }else {
                try {
                    int habitNum = Integer.parseInt(operation);
                    if(habitNum >= 0 && habitNum < HabitsController.Habits.size()){
                        HabitsController.SelectedHabit = HabitsController.Habits.get(habitNum);
                        return ProgramController.UIState.InsideHabit;
                    }else{
                        System.out.println("Invalid habit number");
                    }
                }catch (Exception e) {
                    System.out.println("Invalid command");
                }
            }

        }while (true);
    }

    /**
     * Reads operations to be done with the habit
     * @return Next state of the program loop
     */
    public static ProgramController.UIState ReadUserInputInsideHabit(){
        Scanner scanner = new Scanner(System.in);
        do {
            String operation;
            if (scanner.hasNextLine())
                operation = scanner.nextLine();
            else continue;

            if (operation.equals("exit")){
                System.exit(0);
            }else{
                try {
                    int command = Integer.parseInt(operation);
                    if(command <- 0 || command > 4)
                        System.out.println("Invalid command. Try again");

                    if(command == 1){
                        HabitsController.SelectedHabit.CheckedDates.add(LocalDate.now());
                        HabitsController.SaveHabits();
                        return ProgramController.UIState.InsideHabit;
                    }else if(command == 2){
                        HabitsController.Habits.remove(HabitsController.SelectedHabit);
                        HabitsController.SaveHabits();
                        return ProgramController.UIState.HabitsList;
                    }else if(command == 3){
                        return ProgramController.UIState.HabitsList;
                    }else if(command == 4){
                        System.exit(0);
                    }
                }catch (Exception e) {
                    System.out.println("Not a command");
                }
            }

        }while (true);
    }
}
