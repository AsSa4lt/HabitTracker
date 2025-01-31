package cz.cuni.mff.java;
import cz.cuni.mff.java.controllers.HabitsController;
import cz.cuni.mff.java.controllers.ProgramController;
import cz.cuni.mff.java.coreClasses.Habit;
import cz.cuni.mff.java.helpers.FileManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class HabitsControllerTest {
    private final String testUserDirectory = "testUserDir";
    private final String habitsFilePath = testUserDirectory + "/habits.csv";

    @BeforeEach
    void setUp() throws IOException {
        new File(testUserDirectory).mkdirs();
        File habitsFile = new File(habitsFilePath);
        if (habitsFile.createNewFile()) {
            try (FileWriter writer = new FileWriter(habitsFile)) {
                writer.write("Exercise,2024-01-01,30\n");
                writer.write("Reading,2024-02-01,15\n");
            }
        }
    }

    @AfterEach
    void tearDown() throws IOException {
        FileManager.DeleteFolder(new File(testUserDirectory));
    }

    @Test
    void testSaveHabits() {
        try {
            HabitsController.Habits.clear();
            ProgramController.User = testUserDirectory;
            HabitsController.Habits.add(new Habit("Jogging", LocalDate.of(2024, 3, 1), 10));
            HabitsController.SaveHabits();

            String content = new String(Files.readAllBytes(Paths.get(habitsFilePath)));
            assertTrue(content.contains("Jogging,2024-03-01,10"), "Saved file should contain the new habit.");
        } catch (Exception e) {
            fail("Exception should not be thrown: " + e.getMessage());
        }
    }
}
