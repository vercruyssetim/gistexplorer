package com.cegeka.gistexplorer.progresstracker.util;

import java.io.File;

public class DeleteFolderUtil {
    public static void removeFolder(File folder) {
        File[] files = folder.listFiles();

        if (files != null) {
            for (File f : files) {
                if (f.isDirectory()) {
                    removeFolder(f);
                } else {
                    f.delete();
                }
            }
        }

        folder.delete();
    }
}
