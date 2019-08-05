package com.cegeka.gistexplorer.progresstracker.util;

import com.cegeka.gistexplorer.progresstracker.domain.GistFork;
import com.cegeka.gistexplorer.progresstracker.domain.Progress;

import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.Scanner;


public class ProgressFileReaderUtil {
    private static final String FILE_TITLE = "Switchfully: Java@FIN - Summer Progress Tracker";
    private static final String FILE_NAME = "switchfully-javafin-summer-progress-tracker";
    private static final String READ_DIRECTORY_FORMAT = "%s/%s";

    public static Progress readProgressFromFile(GistFork fork, String directory) {
        Progress progress = new Progress(fork.getOwner().getUsername());
        progress.setLastUpdate(fork.getLastUpdate());
        try (Scanner scanner = new Scanner(Paths.get(String.format(READ_DIRECTORY_FORMAT, directory, FILE_NAME)).toFile())) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (!(FILE_TITLE.equals(line) || line.isEmpty())) {
                    if (line.contains("✔")) {
                        progress.addToCompletedItems(line);
                    } else if (line.contains("❌") || line.contains("[]")) {
                        progress.addToUncompletedItems(line);
                    } else {
                        progress.addToUncertainItems(line);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return progress;
    }
}
