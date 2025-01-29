package cz.cuni.mff.java.helpers;

import cz.cuni.mff.java.coreClasses.Habit;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import cz.cuni.mff.java.controllers.ProgramController;

public class Printer {
    /**
     * Function that takes care of printing habits
     * @param habits List of habits to be printed
     */
    public static void PrintHabitsList(List<Habit> habits) {
        // we are going to print id(number in a list), name and a date of a creation
        // TODO: Add trailing spaces
        for(Habit habit: habits){
            System.out.println(habit);
        }
    }

    public static void PrintHabitDescription(Habit habit) {
        System.out.println("Habit name:         " + habit.Name);
        System.out.println("Creation data:      " + habit.CreationDate);
        System.out.println("Target to complete: " + habit.Target);
        System.out.println("Current streak:     " + habit.GetCurrentStreak() + "Days");
        LocalDate currentDate = LocalDate.now();
        if(habit.CheckedDates.contains(currentDate)){
            System.out.println("Checked today:      " + "Yes");
        }else {
            System.out.println("Checked today:      " + "No");
        }
    }

    public static void PrintMainMenu(){
        System.out.println("Welcome, " + ProgramController.User + "!");
        System.out.println("1. Habits");
        System.out.println("2. Create Habit");
        System.out.println("3. Log out");
        System.out.println("4. Save data");
        System.out.println("5. Exit");
    }
}
