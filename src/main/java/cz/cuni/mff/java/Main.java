package cz.cuni.mff.java;


import cz.cuni.mff.java.controllers.ProgramController;

/**
 * Main class that runs the program
 */
public class Main {
    /**
     * Main function that starts ProgramController
     * @param args arguments
     */
    public static void main(String[] args) {
        ProgramController.SelectUser();
        ProgramController.Run();
    }
}