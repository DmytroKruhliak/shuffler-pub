package com.ddd;

import com.ddd.processor.MusicProcessor;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Shuffler {
    private static final Logger log = Logger.getLogger(Shuffler.class.getName());


    public static void main(String[] args) {
//        File file = new File("D:\\Work\\training\\java\\shuffler\\src\\main\\resources\\music");
        File file = new File("D:\\Documents\\music");
//        File file = new File("G:\\music");

        if (!file.isFile() && !file.isDirectory()) {
            log.warning("Root directory should be 'music', please rename root dir, \n Return.");
            return;
        }

        try {
            log.info("Flash drive has valid root folder - 'music' \n Starting the process...");
            new MusicProcessor().processFolder(file);
        } catch (Exception e) {
            log.log(Level.WARNING, "Exception happen during the shuffling process, see stack trace below:");
            e.printStackTrace();
        }
    }
}
