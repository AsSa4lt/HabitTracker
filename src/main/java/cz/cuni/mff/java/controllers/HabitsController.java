package cz.cuni.mff.java.controllers;

import cz.cuni.mff.java.coreClasses.Habit;
import cz.cuni.mff.java.helpers.Constants;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class HabitsController {
    public static List<Habit> Habits = new ArrayList<>();
    public static Habit SelectedHabit;
    /**
     * Habits are stored in a csv file with only three parameters
     * Name, CreationData, Target
     */
    public static void ReadHabits() throws FileNotFoundException {
        Habits.clear();
        File file = new File(Constants.UsersDirectory + "/" + ProgramController.User + "/" + Constants.HabitsFile);
        // this means that user don't have any habits created
        if(!file.exists()){ return; }

        try(Scanner scanner = new Scanner(file)){
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] split = line.split(",");
                if(split.length != 3){
                    System.out.println("Invalid habit content");
                    continue;
                }
                Habit habit = new Habit(split[0], LocalDate.parse(split[1]),Integer.parseInt(split[2]));
                Habits.add(habit);
            }
        }catch (FileNotFoundException e) {
            System.out.println("File with habits cannot be found");
        }
    }

    public static void CreateNewHabitUser(){
        System.out.println("Creating new habit");
        System.out.println("Enter name for the habit");
        Scanner scanner = new Scanner(System.in);
        String habitName = scanner.nextLine();
        System.out.println("Enter date for the habit");
        LocalDate habitDate;
        do{
            try {
                habitDate = LocalDate.parse(scanner.nextLine());
                break;
            }catch (Exception e){
                System.out.println("Invalid date format, try again");
            }
        }while (true);
        System.out.println("Enter the target for the habit(in days)");
        int target = -1;
        do {
            try {
                target = Integer.parseInt(scanner.nextLine());
                break;
            }catch (Exception e){
                System.out.println("Invalid number format, try again");
            }
        }while (true);
        Habits.add(new Habit(habitName, habitDate, target));
    }

}
