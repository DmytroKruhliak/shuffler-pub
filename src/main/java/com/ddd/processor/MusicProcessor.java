package com.ddd.processor;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Random;
import java.util.UUID;

@Slf4j
public class MusicProcessor implements Processable {
    private static final int LEFT_LIMIT = 97; // letter 'a'
    private static final int RIGHT_LIMIT = 122; // letter 'z'

    @Override
    public void processFolder(File folder) throws IOException {
        log.info(folder.getName() + " in process...");
        for (File file : folder.listFiles()) {
            if (file.isDirectory()) {
                processFolder(file);
            } else if (file.getName().endsWith(".mp3")) {
                renameSong(file);
            }
        }
    }

    private static void renameSong(File song) throws IOException {
        // throws FileAlreadyExistsException, if apply existing name.
        Files.move(song.toPath(), song.toPath().resolveSibling(UUID.randomUUID() + ".mp3"));
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
