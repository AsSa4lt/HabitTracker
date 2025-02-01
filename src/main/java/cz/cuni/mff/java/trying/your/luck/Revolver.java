package cz.cuni.mff.java.trying.your.luck;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Class for revolver to be used in roulette class
 */
public class Revolver {
    /**
     * Stores what was the current shot
     */
    int currentShot;
    /**
     * Randomized int to gamble
     */
    int killShot;

    /**
     * Constructor for class
     */
    public Revolver(){
        currentShot = 1;
        killShot = ThreadLocalRandom.current().nextInt(1, 6);
    }

    /**
     * Function where you or a computer makes a shot
     * @return if the person who makes a shot lost
     */
    public boolean Shot(){
        System.out.println("Shot: " + currentShot);
        if(currentShot == killShot){
            System.out.println("It wasn't an empty shot");
            return true;
        }else{
            currentShot++;
            System.out.println("It was an empty shot");
            return false;
        }
    }

    /**
     * What is a chance that this shot is going to be lethal
     * @return a chance
     */
    public double GetCurrentProbability(){
        // get how many shots has left
        // divide by maximum number of the shots
        return (double)(1) / (double)(6-currentShot+1);
    }
}
