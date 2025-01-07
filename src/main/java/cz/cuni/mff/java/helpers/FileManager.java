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
        for (File file : files) {
            if (file.isDirectory()) {
                DeleteFolder(file);
            }else{
                file.delete();
            }
        }
        folder.delete();
    }
}
