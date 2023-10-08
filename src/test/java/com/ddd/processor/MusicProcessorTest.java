package com.ddd.processor;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class MusicProcessorTest {

    private static final String MUSIC_PATH = "src/test/resources/music";

    private final MusicProcessor processor = new MusicProcessor();
    private static File musicFolderContent;

    @BeforeAll
    static void init() {
        musicFolderContent = new File(MUSIC_PATH);
        assertNotNull(musicFolderContent);
        assertNotNull(musicFolderContent.listFiles());
    }

    @AfterAll
    static void cleanUp() {
    }

    @Test
    void shouldNotChangeFilesStructure() {
        // given
        String[] fileNames = musicFolderContent.list();

        // when
        processor.processFolder(musicFolderContent);

        // then
        File updatedFolderContent = new File(MUSIC_PATH);
        String[] updatedFileNames = updatedFolderContent.list();

        assertNotNull(fileNames);
        assertNotNull(updatedFileNames);
        assertEquals(fileNames.length, updatedFileNames.length);
        assertFalse(Arrays.equals(fileNames, updatedFileNames));
    }

    @Test
    public void shouldNotRenameFolder() {
        // given
        File[] folders = musicFolderContent.listFiles(File::isDirectory);
        assertNotNull(folders);

        List<String> topLevelFolderNames = Arrays.stream(folders)
                .map(File::getName)
                .collect(Collectors.toList());

        // when
        processor.processFolder(musicFolderContent);

        // then
        assertFalse(topLevelFolderNames.isEmpty());
        assertNotNull(topLevelFolderNames);

        File[] processedFiles = new File(MUSIC_PATH).listFiles(File::isDirectory);
        assertNotNull(processedFiles);

        List<String> processedFolders = Arrays.stream(processedFiles)
                .map(File::getName)
                .collect(Collectors.toList());

        assertNotNull(processedFolders);
        assertEquals(topLevelFolderNames, processedFolders);
    }

    @Test
    public void shouldRenameFirstFile() {
        // given
        File[] musicFiles = musicFolderContent.listFiles(File::isFile);
        assertNotNull(musicFiles);

        String firstFileName = Arrays.stream(musicFiles)
                .findFirst()
                .map(File::getName)
                .orElse("null");

        assertNotEquals("null", firstFileName);

        // when
        processor.processFolder(musicFolderContent);

        // then
        File[] processedFiles = new File(MUSIC_PATH).listFiles(File::isFile);
        assertNotNull(processedFiles);
        String firstRenamedFile = Arrays.stream(processedFiles)
                .findFirst()
                .map(File::getName)
                .orElse("null");

        assertNotNull(firstRenamedFile);
        assertNotEquals("null", firstRenamedFile);
        assertNotNull(firstFileName, firstRenamedFile);
    }

    @Test
    public void shouldHandleEmptyFolder() {
        // when
        assertDoesNotThrow(() -> processor.processFolder(new File("someNonExistingPath")));
    }

    private List<String> getFileNames() {
        File files = new File(MUSIC_PATH);
        return Arrays.asList(Objects.requireNonNull(files.list()));
    }

}