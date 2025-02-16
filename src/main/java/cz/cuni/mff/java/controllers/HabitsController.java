package cz.cuni.mff.java.controllers;

import cz.cuni.mff.java.coreClasses.Habit;
import cz.cuni.mff.java.helpers.Constants;
import cz.cuni.mff.java.helpers.FileManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Class to provide interface for dealing with habits
 */
public class HabitsController {
    public static List<Habit> Habits = new ArrayList<>();
    public static Habit SelectedHabit;
    /**
     * Habits are stored in a csv file with only three parameters
     * Name, CreationData, Target
     */
    public static void ReadHabits() throws FileNotFoundException {
        Habits.clear();
        File file = new File(Constants.UsersDirectory + File.separator + ProgramController.User + File.separator + Constants.HabitsFile);
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
                // Now read data about the habit from the .txt file
                File habitFile = new File(Constants.UsersDirectory + File.separator + ProgramController.User + File.separator + habit.Name + ".txt");
                Scanner scannerHabit = new Scanner(habitFile);
                if (scannerHabit.hasNextLine()) {
                    String data = scannerHabit.nextLine();
                    String[] dates = data.split(",");
                    for (String date : dates) {
                        LocalDate localDate = LocalDate.parse(date);
                        habit.CheckedDates.add(localDate);
                    }
                }
                Habits.add(habit);
            }
        }catch (FileNotFoundException e) {
            System.out.println("File with habits cannot be found");
        }
    }

    /**
     * Function to create a new habit from user's input
     */
    public static void CreateNewHabitUser(){
        System.out.println("Creating new habit");
        System.out.println("Enter name for the habit");
        Scanner scanner = new Scanner(System.in);
        String habitName = scanner.nextLine();
        LocalDate habitDate = LocalDate.now();
        System.out.println("Enter the target for the habit(in days)");
        int target;
        do {
            try {
                target = Integer.parseInt(scanner.nextLine());
                break;
            }catch (Exception e){
                System.out.println("Invalid number format, try again");
            }
        }while (true);
        Habits.add(new Habit(habitName, habitDate, target));
        try {
            SaveHabits();
        }catch (IOException e){
            System.out.println("Error while saving habits");
        }

    }

    /**
     * Function to save habits to a file
     * Name, CreationData, Target
     */
    public static void SaveHabits() throws IOException {
        File file = new File(Constants.UsersDirectory + File.separator+ ProgramController.User + File.separator + Constants.HabitsFile);
        if (!file.exists()){
           System.out.println("Something went wrong. Creating new habit.csv");
        }
        FileWriter writer = new FileWriter(file);

        // at first save all habits list to a csv
        for(Habit habit : Habits){
            // write to a file name, data, target
            writer.write(habit.Name + "," + habit.CreationDate + "," + habit.Target + ",\n");
        }
        writer.close();
        // now we can write each habit data
        for(Habit habit : Habits){
            // get a path for a txt for a habit
            File fileHabit = new File(Constants.UsersDirectory + File.separator+ ProgramController.User + File.separator + habit.Name + ".txt");
            writer = new FileWriter(fileHabit);
            // write data for the habit in this file
            for (LocalDate date : habit.CheckedDates){
                writer.write(date.toString() + ",");
            }
            writer.close();
        }
    }

}
