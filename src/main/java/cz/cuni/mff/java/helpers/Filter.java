package cz.cuni.mff.java.helpers;

import cz.cuni.mff.java.coreClasses.Habit;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that filters habits by different parameters
 * 1. Creation date
 * 2. Created before
 * 3. Created after
 * 4. Name contains
 * 5. Target bigger or equals
 * 6. Target smaller or equals
 * 7. Streak bigger or equals
 * 8. Streak smaller or equals
 */
public class Filter {
    /**
     * Method to get habits with required date
     * @param habits, where we look for a required date
     * @param creationDate parameter to look after
     * @return list of habits with a required date
     */
    public static List<Habit> GetByCreationDate(List<Habit> habits, LocalDate creationDate) {
        List<Habit> results = new ArrayList<>();
        for (Habit habit : habits) {
            if(habit.CreationDate.equals(creationDate)) {
                results.add(habit);
            }
        }
        return results;
    }

    /**
     * Method to provide habits that were created before
     * @param habits list in which we are looking for parameter
     * @param creationDate date we are looking for
     * @return list of a habits that were created before the date
     */
    public static List<Habit> GetCreatedBefore(List<Habit> habits, LocalDate creationDate) {
        List<Habit> results = new ArrayList<>();
        for (Habit habit : habits) {
            if(habit.CreationDate.isBefore(creationDate)) {
                results.add(habit);
            }
        }
        return results;
    }

    /**
     * Returns the list of habits that were created after the provided date
     * @param habits list of habits where we should look for
     * @param creationDate parameter to look after
     * @return returns a list of habits that were created after the date
     */
    public static List<Habit> GetCreatedAfter(List<Habit> habits, LocalDate creationDate) {
        List<Habit> results = new ArrayList<>();
        for (Habit habit : habits) {
            if(habit.CreationDate.isAfter(creationDate)) {
                results.add(habit);
            }
        }
        return results;
    }

    /**
     * Method to get a list of habits, where their names contains string as a parameter
     * @param habits list of habits where we should look for
     * @param name String that we should look after in habits
     * @return returns a list of habits, which names contains this String
     */
    public static List<Habit> GetNameContaining(List<Habit> habits, String name) {
        List<Habit> results = new ArrayList<>();
        for (Habit habit : habits) {
            if(habit.Name.contains(name)) {
                results.add(habit);
            }
        }
        return results;
    }

    /**
     * Method to get a list of habits, where target is bigger or equals to the parameter
     * @param habits list of habits where we should look for
     * @param target Value of target that we should look for
     * @return list of habits where target is bigger or equals to a parameter
     */
    public static List<Habit> GetTargetBiggerOrEquals(List<Habit> habits, int target){
        List<Habit> results = new ArrayList<>();
        for (Habit habit : habits) {
            if(habit.Target >= target) {
                results.add(habit);
            }
        }
        return results;
    }

    /**
     * Method to find a list of habits, where target is smaller or equals to the parameter
     * @param habits list of habits where we should look for
     * @param target Value of target where we should look for
     * @return list of habits where target is smaller or equals
     */
    public static List<Habit> GetTargetSmallerOrEquals(List<Habit> habits, int target){
        List<Habit> results = new ArrayList<>();
        for (Habit habit : habits) {
            if(habit.Target <= target) {
                results.add(habit);
            }
        }
        return results;
    }

    /**
     * Method to find a list of habits, where streak is bigger or equals to the parameter
     * @param habits list of habits where we should look for
     * @param streak Value of streak that we should look for
     * @return list of habits where target is bigger or equals
     */
    public static List<Habit> GetStreakBiggerOrEquals(List<Habit> habits, int streak){
        List<Habit> results = new ArrayList<>();
        for (Habit habit : habits) {
            if(habit.GetCurrentStreak() >= streak) {
                results.add(habit);
            }
        }
        return results;
    }

    /**
     * Method to find a list of habits, where streak is smaller or equals to the parameter
     * @param habits list of habits where we should look for
     * @param streak Value of streak that we should look for
     * @return list of habits where target is smaller or equals
     */
    public static List<Habit> GetStreakSmallerOrEquals(List<Habit> habits, int streak){
        List<Habit> results = new ArrayList<>();
        for (Habit habit : habits) {
            if(habit.GetCurrentStreak() <= streak) {
                results.add(habit);
            }
        }
        return results;
    }
}
