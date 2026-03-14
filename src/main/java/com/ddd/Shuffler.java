package com.ddd;

import com.ddd.processor.MusicProcessor;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Shuffler {
    private static final Logger log = Logger.getLogger(Shuffler.class.getName());


    public static void main(String[] args) {
        File file = new File("music");

        if (!file.exists() || !file.isDirectory()) {
            log.warning("The 'music' directory was not found in the current directory. Please make sure the 'music' folder is present where the jar is running.");
            return;
        }

        try {
            log.info("Found 'music' folder. Starting the process...");
            new MusicProcessor().processFolder(file);
        } catch (Exception e) {
            log.log(Level.WARNING, "Exception happen during the shuffling process, see stack trace below:");
            e.printStackTrace();
        }
    }
}
