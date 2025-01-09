package cz.cuni.mff.java.coreClasses;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;

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
     * Default value is -1, which indicated that there is no target for this habbit
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

    public boolean AreMissedDays(){
        // get number of days between today and Creation date and compare to the size of CheckedDays
        for(LocalDate date = CreationDate; date.isBefore(LocalDate.now().minusDays(1)); date.plusDays(1)){
            if(!CheckedDates.contains(date)){
                return false;
            }
        }
        return true;
    }

    public int GetCurrentStreak(){
        int currentStreak = 0;
        for(LocalDate date = CreationDate; date.isBefore(LocalDate.now().minusDays(1)); date.plusDays(1)){
            if(!CheckedDates.contains(date)){
                currentStreak = 0;
            }else {
                currentStreak++;
            }
        }
        return currentStreak;
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
