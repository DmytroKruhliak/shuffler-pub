package com.ddd;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Random;

public class Shuffler {
    private static final int LEFT_LIMIT = 97; // letter 'a'
    private static final int RIGHT_LIMIT = 122; // letter 'z'

    public static void main(String[] args) {
//        File file = new File("D:\\programing\\java\\shuffler\\src\\main\\java\\music");
        File file = new File(".\\music");
        if (!file.isFile() && !file.isDirectory()) {
            System.out.println("Root directory should be 'music', please rename root dir");
            System.out.println("Returning...");
            return;
        }

        System.out.println("Flash rive has valid root folder - 'music'");
        System.out.println("Starting the process...");

        try {

            handleDir(file);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("Process of Shuffling - finished!");
        }
    }

    private static void handleDir(File folder) throws IOException {
        System.out.println(folder.getName() + " in process...");
        for (File file : folder.listFiles()) {
            if (file.isDirectory()) {
                handleDir(file);
            } else if (file.getName().endsWith(".mp3")) {
                renameSong(file);
            }
        }
    }

    private static void renameSong(File song) throws IOException {
        Files.move(song.toPath(), song.toPath().resolveSibling(generateRandomString() + ".mp3"));
    }

    private static String generateRandomString() {
        Random rnd = new Random();
        int length = 10 + rnd.nextInt(5);
        StringBuilder buffer = new StringBuilder(length);

        for (int i = 0; i <= length; i++) {
            buffer.append((char) (LEFT_LIMIT + rnd.nextInt(RIGHT_LIMIT - LEFT_LIMIT)));
        }

        return buffer.toString();
    }
}
