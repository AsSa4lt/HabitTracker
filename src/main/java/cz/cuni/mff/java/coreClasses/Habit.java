package cz.cuni.mff.java.coreClasses;

import java.time.LocalDate;
import java.util.HashSet;

/**
 * Class that stores data about the habit from files
 */
public class Habit {

    /**
     * Name of the habit
     */
    public String Name;
    /**
     * Date of the creation
     */
    public LocalDate CreationDate;
    /**
     * User selected target for a habit
     * Default value is -1, which indicated that there is no target for this habit
     */
    public int Target = -1;

    /**
     * Hashset of a dates when user checked habit
     */
    public HashSet<LocalDate> CheckedDates = new HashSet<>();

    /**
     * Basic constructor for Habit
     * @param Name Habit name
     * @param CreationDate Date, when habit has been created
     * @param Target Target, when habit can be marked as done
     */
    public Habit(String Name, LocalDate CreationDate, int Target) {
        this.Name = Name;
        this.CreationDate = CreationDate;
        this.Target = Target;
    }


    /**
     * Function that goes through checked days and looks for a streak
     * @return Returns current streak
     */
    public int GetCurrentStreak(){
        int currentStreak = 0;
        for(LocalDate date = CreationDate; date.isBefore(LocalDate.now().plusDays(1)); date = date.plusDays(1)){
            if(!CheckedDates.contains(date)){
                currentStreak = 0;
            }else {
                currentStreak++;
            }
        }
        return currentStreak;
    }


    /**
     * Method that provides interface to check today
     */
    public void CheckToday(){
        LocalDate today = LocalDate.now();
        CheckedDates.add(today);
    }

    /**
     * Makes habit completed, method for roulette
     * Made exclusively for roulette class
     */
    public void CheatOnHabit(){
        CreationDate = LocalDate.now().minusDays(Target + 10);
        for(LocalDate date = CreationDate; date.isBefore(LocalDate.now()); date = date.plusDays(1)){
            if(!CheckedDates.contains(date)){
                CheckedDates.add(date);
            }
        }
        CheckToday();
    }

    /**
     * Method with simple printing of a habit
     * @return String with a short description of a habit
     */
    @Override public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(Name).append(" ").append(CreationDate).append(" ").append(Target);
        return sb.toString();
    }
}
