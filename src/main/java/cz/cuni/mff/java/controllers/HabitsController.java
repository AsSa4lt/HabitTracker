package cz.cuni.mff.java.controllers;

import cz.cuni.mff.java.coreClasses.Habit;

import java.util.ArrayList;
import java.util.List;

public class HabitsController {
    public List<Habit> Habits = new ArrayList<>();

    public void ReadHabits(){
        Habits.clear();
    }


}
