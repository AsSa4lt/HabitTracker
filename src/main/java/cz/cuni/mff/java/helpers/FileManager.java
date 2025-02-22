package cz.cuni.mff.java.helpers;

import java.io.File;

/**
 * Class to provide reusable operations with files
 * @author Rostyslav Liapkin
 */
public class FileManager {
    /**
     * Function to delete selected folder and all it's content
     * @param folder Name of a folder to be deleted
     */
    public static void DeleteFolder(File folder){
        File[] files = folder.listFiles();
        if (files == null) return;
        // we are calling this function recursive to delete all files inside
        for (File file : files) {
            // if some files, call again
            if (file.isDirectory()) {
                DeleteFolder(file);
            }
            // in no files, just delete this file
            else{
                file.delete();
            }
        }
        folder.delete();
    }

    /**
     * Creates folder if it doesn't exist
     * @param folder that is created
     */
    public static void CreateFolder(File folder){
        if (!folder.exists()) folder.mkdir();
    }
}
