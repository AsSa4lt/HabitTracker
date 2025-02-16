package cz.cuni.mff.java.trying.your.luck;

import cz.cuni.mff.java.controllers.HabitsController;
import cz.cuni.mff.java.controllers.ProgramController;
import cz.cuni.mff.java.coreClasses.Habit;
import cz.cuni.mff.java.helpers.Constants;
import cz.cuni.mff.java.helpers.FileManager;

import java.io.File;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * Class to play roulette
 */
public class Roulette {

    /**
     * Main class for mini-game
     * @return New state of the game
     */
    public static ProgramController.UIState PlayRoulette(){
        System.out.println("If you want to stop now, write STOP");
        System.out.println("If you want to just to test it, write TEST");
        System.out.println("It's just going to simulate game without any consequences");
        Scanner scanner = new Scanner(System.in);

        boolean safeMode = false;
        // Give a last change to a user to stop
        do {
            if(scanner.hasNextLine()){
                String line = scanner.nextLine();
                if(line.equals("STOP")){
                    return ProgramController.UIState.MainPage;
                } else if (line.equals("TEST")) {
                    System.out.println("You are in a safe mode");
                    safeMode = true;
                    break;
                } else {
                    System.out.println("You are gambling now");
                    break;
                }
            }
        }while (true);

        Revolver revolver = new Revolver();

        int step = 0;
        while (true) {
            System.out.println("Current chance to get shot is: "  + (int)(revolver.GetCurrentProbability() * 100) + "%");
            if(step%2 == 0){
                // if step is divisible by two, it's your step
                boolean youLost = revolver.Shot();
                if(youLost){
                    if(!safeMode)
                        UserLost();
                    else
                        System.out.println("You lost, but it's a safe mode");
                    return ProgramController.UIState.Logging;
                }else {
                    System.out.println("You survived, nice");
                }
            }else {
                boolean youWon = revolver.Shot();
                if(youWon){
                    if(!safeMode) {
                        System.out.println("You won, cheater");
                        Cheat();
                    }else {
                        System.out.println("You won, but it's a safe mode");
                        System.out.println("No risc - no reward");
                    }
                    return ProgramController.UIState.MainPage;
                }else {
                    System.out.println("Computer has survived");
                }
            }
            step++;
            // Make user wait to get more interaction
            try {
                TimeUnit.SECONDS.sleep(3);
            }catch (InterruptedException e){
                System.out.println("Shouldn't happen");
            }
        }
    }

    /**
     * Actions to be done if user has lost
     */
    private static void UserLost(){
        // in case you lost
        System.out.println("You lost, good bye");
        System.out.println("All your files are going to be deleted in:");
        for (int i = 5; i > 0; i--){
            System.out.println(i);
        }
        // all your user files are going to be deleted
        File folder = new File(Constants.UsersDirectory + File.separator + ProgramController.User);
        FileManager.DeleteFolder(folder);
        System.out.println("All your files have been deleted, you should just complete your habits");
    }

    /**
     * Method that makes all habits completed
     */
    private static void Cheat(){
        for(Habit habit: HabitsController.Habits){
            habit.CheatOnHabit();
        }
    }
}
