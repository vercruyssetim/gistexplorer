package com.cegeka.gistexplorer.progresstracker.progress;

import com.cegeka.gistexplorer.progresstracker.gist.Fork;
import com.cegeka.gistexplorer.progresstracker.gist.ForkDetail;
import com.cegeka.gistexplorer.progresstracker.teams.TeamRepository;
import org.springframework.stereotype.Service;


@Service
public class ProgressReader {

    private TeamRepository teamRepository;

    public ProgressReader(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public Progress readProgressFromFile(ForkDetail forkDetail) {
        Progress progress = new Progress(forkDetail.getOwner().getUsername());
        progress.setUserInFodFin(teamRepository.isUserInFodFin(forkDetail.getOwner().getUsername()));
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

    public Progress readProgressFromFile(Fork fork, String content) {
        Progress progress = new Progress(fork.getOwner().getUsername());
        progress.setUserInFodFin(teamRepository.isUserInFodFin(fork.getOwner().getUsername()));
        progress.setLastUpdate(fork.getLastUpdate());
        progress.setUrl(fork.getHtmlUrl());

        for (String line : content.split("\n")) {
            if (line.contains("[")) {
                if (line.contains("✔") || line.contains("[:heavy_check_mark:]")) {
                    progress.addToCompletedItems(line);
                } else {
                    progress.addToUncompletedItems(line);
                }
            }
        }
        progress.calculateProgress();
        return progress;
    }
}
