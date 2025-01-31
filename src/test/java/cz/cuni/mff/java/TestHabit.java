package cz.cuni.mff.java;
import cz.cuni.mff.java.coreClasses.Habit;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestHabit {
    @Test
    void testEmptyCheckedDates() {
        Habit habit = new Habit("Exercise", LocalDate.now().minusDays(5), -1);
        assertEquals(0, habit.GetCurrentStreak(), "Streak should be 0 when no dates are checked.");
    }

    @Test
    void testContinuousStreak() {
        Habit habit = new Habit("Exercise", LocalDate.now().minusDays(5), -1);
        for (int i = 0; i < 6; i++) {
            habit.CheckedDates.add(LocalDate.now().minusDays(i));
        }
        assertEquals(6, habit.GetCurrentStreak(), "Streak should count all consecutive days.");
    }

    @Test
    void testBrokenStreak() {
        Habit habit = new Habit("Exercise", LocalDate.now().minusDays(5), -1);
        habit.CheckedDates.add(LocalDate.now().minusDays(5));
        habit.CheckedDates.add(LocalDate.now().minusDays(4));
        habit.CheckedDates.add(LocalDate.now().minusDays(2)); // Breaks streak
        habit.CheckedDates.add(LocalDate.now().minusDays(1));
        habit.CheckedDates.add(LocalDate.now());
        assertEquals(3, habit.GetCurrentStreak(), "Streak should reset after a missing day.");
    }
}
