package cz.cuni.mff.java.helpers;

import cz.cuni.mff.java.coreClasses.Habit;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

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
        for(LocalDate date: habit.CheckedDates){

        }
    }
}
