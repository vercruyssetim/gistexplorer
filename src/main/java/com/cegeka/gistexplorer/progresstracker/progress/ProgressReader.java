package com.cegeka.gistexplorer.progresstracker.progress;

import com.cegeka.gistexplorer.progresstracker.gist.ForkDetail;
import org.springframework.stereotype.Service;


@Service
public class ProgressReader {

    public Progress readProgressFromFile(ForkDetail forkDetail) {
        Progress progress = new Progress(forkDetail.getOwner().getUsername());
        progress.setLastUpdate(forkDetail.getLastUpdate());
        progress.setUrl(forkDetail.getHtmlUrl());

        for (String line : forkDetail.getContent().split("\n")) {
            if (line.contains("[")) {
                if (line.contains("✔")) {
                    progress.addToCompletedItems(line);
                } else if (line.contains("❌") || line.contains("[]")) {
                    progress.addToUncompletedItems(line);
                } else {
                    progress.addToUncertainItems(line);
                }
            }
        }
        progress.calculateProgress();
        return progress;
    }
}
