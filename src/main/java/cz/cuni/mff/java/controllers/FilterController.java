package cz.cuni.mff.java.controllers;

import cz.cuni.mff.java.coreClasses.Habit;
import cz.cuni.mff.java.helpers.Filter;
import cz.cuni.mff.java.helpers.Printer;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

/**
 * Class to read and deal with users input in filter
 */
public class FilterController {

    /**
     * Main function of the classes that scans what user is going to filter, or user just wants to get back to the menu
     * @return Next state of the program
     */
    public static ProgramController.UIState ReadUserInput(){
        Scanner scanner = new Scanner(System.in);
        do{
            String input;
            if(scanner.hasNextLine())
                input = scanner.nextLine();
            else{
                System.out.println("Please enter a valid input");
                continue;
            }

            if(input.equals("exit"))
                System.exit(0);

            // now we need to parse command to an int
            try {
                int command = Integer.parseInt(input);
                if(command <- 0 || command > 10){
                    System.out.println("Please enter a valid input");
                    continue;
                }

                if(command == 9)
                    return ProgramController.UIState.MainPage;
                else if(command == 10)
                    System.exit(0);
                else if(command == 1)
                    UserByACreationDate();
                else if(command == 2)
                    UserHabitsBeforeProvidedDate();
                else if (command == 3)
                    UserHabitsAfterProvidedDate();
                else if (command == 4)
                    UserHabitsThatContainsString();
                else if (command == 5)
                    UserHabitsTargetBiggerOrEquals();
                else if (command == 6)
                    UserHabitsTargetSmallerOrEquals();
                else if (command == 7)
                    UserHabitsStreakBiggerOrEquals();
                else if (command == 8)
                    UserHabitsStreakSmallerOrEquals();

                return ProgramController.UIState.FilteringHabits;
            }catch (Exception e) {
                System.out.println("Please enter a valid command(number)");
            }
        }while (scanner.hasNextLine());
        return ProgramController.UIState.FilteringHabits;
    }

    /**
     * Scans date from user and prints habits with provided date
     */
    private static void UserByACreationDate(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the creation date");
        String creationDate = scanner.nextLine();
        LocalDate date = LocalDate.parse(creationDate);
        List<Habit> list = Filter.GetByCreationDate(HabitsController.Habits, date);
        Printer.PrintHabitsList(list);
    }

    /**
     * Scans date from user and prints habits that were created before the provided date
     */
    private static void UserHabitsBeforeProvidedDate(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the creation date");
        String creationDate = scanner.nextLine();
        LocalDate date = LocalDate.parse(creationDate);
        List<Habit> list = Filter.GetCreatedBefore(HabitsController.Habits, date);
        Printer.PrintHabitsList(list);
    }


    /**
     * Scans date from user and prints habits that were created after the provided dates
     */
    private static void UserHabitsAfterProvidedDate(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the creation date");
        String creationDate = scanner.nextLine();
        LocalDate date = LocalDate.parse(creationDate);
        List<Habit> list = Filter.GetCreatedAfter(HabitsController.Habits, date);
        Printer.PrintHabitsList(list);
    }


    /**
     * Scans a string from user and prints habits which names contains this string
     */
    private static void UserHabitsThatContainsString(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the string");
        String string = scanner.nextLine();
        List<Habit> list = Filter.GetNameContaining(HabitsController.Habits, string);
        Printer.PrintHabitsList(list);
    }


    /**
     * Scans a target from user and prints habits where target is bigger or equals to this value
     */
    private static void UserHabitsTargetBiggerOrEquals(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the target");
        String target = scanner.nextLine();
        int targetInteger = Integer.parseInt(target);
        List<Habit> list = Filter.GetTargetBiggerOrEquals(HabitsController.Habits, targetInteger);
        Printer.PrintHabitsList(list);
    }


    /**
     * Scans a target from user and prints habits where target is smaller or equals to this value
     */
    private static void UserHabitsTargetSmallerOrEquals(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the target");
        String target = scanner.nextLine();
        int targetInteger = Integer.parseInt(target);
        List<Habit> list = Filter.GetTargetSmallerOrEquals(HabitsController.Habits, targetInteger);
        Printer.PrintHabitsList(list);
    }


    /**
     * Scans a streak from user and prints habits where streak is bigger or equals to this value
     */
    private static void UserHabitsStreakBiggerOrEquals(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the streak");
        String streak = scanner.nextLine();
        int streakInteger = Integer.parseInt(streak);
        List<Habit> list = Filter.GetStreakBiggerOrEquals(HabitsController.Habits, streakInteger);
        Printer.PrintHabitsList(list);
    }


    /**
     * Scans streak from user and prints habits where streak is smaller or equals to this value
     */
    private static void UserHabitsStreakSmallerOrEquals(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the streak");
        String streak = scanner.nextLine();
        int streakInteger = Integer.parseInt(streak);
        List<Habit> list = Filter.GetStreakSmallerOrEquals(HabitsController.Habits, streakInteger);
        Printer.PrintHabitsList(list);
    }
}
