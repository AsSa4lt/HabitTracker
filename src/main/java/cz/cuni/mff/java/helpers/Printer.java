package cz.cuni.mff.java.helpers;

import cz.cuni.mff.java.coreClasses.Habit;

import java.time.LocalDate;
import java.util.List;
import cz.cuni.mff.java.controllers.ProgramController;

/**
 * Class that takes care of printing different stuff
 */
public class Printer {

    /**
     * Wrapper that is needed to have difference
     * between printing habits in filer
     * and from HabitsController
     * @param habits habits that we need to print
     */
    public static void PrintListHabitsWrapperForMenuController(List<Habit> habits) {
        System.out.println("Input is a number of a habit, menu, or exit");
        PrintHabitsList(habits);
    }
    /**
     * Function that takes care of printing habits
     * @param habits List of habits to be printed
     */
    public static void PrintHabitsList(List<Habit> habits) {
        if (habits.isEmpty()) {
            System.out.println("No habits found");
            return;
        }

        // we are going to print name, date of a creation and a target
        // Find the longest name from habits
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
        // iterate through habits
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

    /**
     * Function that takes care of printing pretty description of a habit
     * @param habit that is going to be printed
     */
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

    /**
     * Function that prints the main menu
     */
    public static void PrintMainMenu(){
        System.out.println("Welcome, " + ProgramController.User + "!");
        System.out.println("1. Habits");
        System.out.println("2. Filter habits");
        System.out.println("3. Create Habit");
        System.out.println("4. Try your luck(Not recommended)");
        System.out.println("5. Log out");
        System.out.println("6. Save data");
        System.out.println("7. Exit");
    }

    /**
     * Function that prints data for filter menu controller
     */
    public static void PrintFilterMenu(){
        System.out.println("Select command");

        System.out.println("1. Find by a creation date");
        System.out.println("2. Find habits that were created before the provided date");
        System.out.println("3. Find habits that were created after the provided date");
        System.out.println("4. Find habits which name contains provided string");
        System.out.println("5. Find habits that have target that is bigger or equals to the provided target");
        System.out.println("6. Find habits that have target that is smaller or equals to the provided target");
        System.out.println("7. Find habits that have streak that is bigger or equals to the provided target");
        System.out.println("8. Find habits that have streak that is smaller or equals to the provided target");
        System.out.println("9. Back to main menu");
        System.out.println("10. Exit");
    }

    /**
     * Function to print rules of the roulette
     */
    public static void PrintRouletteRules(){
        System.out.println("Rules are simple");
        System.out.println("Both you and computer make a shot in a roulette");
        System.out.println("If you win, you get all your habits completed");
        System.out.println("If you loose, computer deleted all your user files");
    }
}
