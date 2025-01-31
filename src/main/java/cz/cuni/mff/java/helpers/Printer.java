package cz.cuni.mff.java.helpers;

import cz.cuni.mff.java.coreClasses.Habit;

import java.time.LocalDate;
import java.util.List;
import cz.cuni.mff.java.controllers.ProgramController;

public class Printer {
    /**
     * Function that takes care of printing habits
     * @param habits List of habits to be printed
     */
    public static void PrintHabitsList(List<Habit> habits) {
        // we are going to print name, date of a creation and a target
        // Find the longest name from habits
        System.out.println("Write a number of habit, menu or exit");
        int longestName = -1;
        for(Habit habit: habits){
            if(habit.Name.length() > longestName){
                longestName = habit.Name.length();
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Name");
        sb.append(" ".repeat(Math.max(0, longestName - 4)));
        sb.append(Constants.SpaceBetweenColumns).append("Date      ");
        sb.append(Constants.SpaceBetweenColumns).append("Streak/Target");
        System.out.println(sb);
        for(Habit habit: habits){
            StringBuilder sbHabit = new StringBuilder();
            sbHabit.append(habit.Name);
            sbHabit.append(" ".repeat(Math.max(0, longestName - habit.Name.length())));
            sbHabit.append(Constants.SpaceBetweenColumns);
            sbHabit.append(habit.CreationDate);
            sbHabit.append(Constants.SpaceBetweenColumns);
            sbHabit.append(habit.GetCurrentStreak()).append("/").append(habit.Target);
            System.out.println(sbHabit);
        }
    }

    public static void PrintHabitDescription(Habit habit) {
        System.out.println("Habit name:         " + habit.Name);
        System.out.println("Creation data:      " + habit.CreationDate);
        System.out.println("Target to complete: " + habit.Target);
        System.out.println("Current streak:     " + habit.GetCurrentStreak() + " Days");
        LocalDate currentDate = LocalDate.now();
        if(habit.CheckedDates.contains(currentDate)){
            System.out.println("Checked today:      " + "Yes");
        }else {
            System.out.println("Checked today:      " + "No");
        }
        System.out.println("1. Check today");
        System.out.println("2. Delete habit");
        System.out.println("3. Habits list");
        System.out.println("4. Exit");
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
