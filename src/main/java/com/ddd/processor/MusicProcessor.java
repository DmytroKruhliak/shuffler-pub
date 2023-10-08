package com.ddd.processor;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Random;
import java.util.UUID;
import java.util.logging.Logger;

import static java.util.Optional.ofNullable;

//@Slf4j
public class MusicProcessor implements Processable {
    private static final Logger log = Logger.getLogger(MusicProcessor.class.getName());
    private static final int LEFT_LIMIT = 97; // letter 'a'
    private static final int RIGHT_LIMIT = 122; // letter 'z'

    @Override
    public void processFolder(File folder) {
        log.info(folder.getName() + " in progress...");

        ofNullable(folder.listFiles()).ifPresent(files -> Arrays.asList(files).forEach(file -> {
            if (file.isDirectory()) {
                processFolder(file);
            } else if (file.getName().endsWith(".mp3")) {
                renameSong(file);
            }
        }));
    }

    private static void renameSong(File song) {
        // throws FileAlreadyExistsException, if apply existing name.
        try {
            Files.move(song.toPath(), song.toPath().resolveSibling(UUID.randomUUID() + ".mp3"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String generateRandomString() {
        Random rnd = new Random();
        int length = 10 + rnd.nextInt(5);
        StringBuilder buffer = new StringBuilder(length);

        for (int i = 0; i <= length; i++) {
            buffer.append((char) (LEFT_LIMIT + rnd.nextInt(RIGHT_LIMIT - LEFT_LIMIT)));
        }

        return buffer.toString();
    }
}
