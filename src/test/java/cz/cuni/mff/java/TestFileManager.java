package cz.cuni.mff.java;

import cz.cuni.mff.java.helpers.FileManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestFileManager {
    private File testFolder;

    @BeforeEach
    void setUp() {
        testFolder = new File("testDir");
        FileManager.CreateFolder(testFolder);
    }

    @AfterEach
    void tearDown() {
        FileManager.DeleteFolder(testFolder);
    }

    @Test
    void testCreateFolder() {
        assertTrue(testFolder.exists() && testFolder.isDirectory(), "Folder should be created.");
    }

    @Test
    void testDeleteFolder() {
        FileManager.DeleteFolder(testFolder);
        assertFalse(testFolder.exists(), "Folder should be deleted.");
    }

    @Test
    void testDeleteFolderWithFiles() throws IOException {
        File file = new File(testFolder, "testFile.txt");
        assertTrue(file.createNewFile(), "Test file should be created.");

        FileManager.DeleteFolder(testFolder);
        assertFalse(testFolder.exists(), "Folder and its contents should be deleted.");
    }

    @Test
    void testDeleteNestedFolders() {
        File nestedFolder = new File(testFolder, "nested");
        FileManager.CreateFolder(nestedFolder);
        assertTrue(nestedFolder.exists() && nestedFolder.isDirectory(), "Nested folder should be created.");

        FileManager.DeleteFolder(testFolder);
        assertFalse(testFolder.exists(), "Folder and its nested contents should be deleted.");
    }
}
