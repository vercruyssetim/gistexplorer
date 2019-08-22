package com.cegeka.gistexplorer.progresstracker.progress;

import com.cegeka.gistexplorer.progresstracker.gist.ForkInformation;
import com.cegeka.gistexplorer.progresstracker.teams.TeamRepository;
import org.springframework.stereotype.Service;


@Service
public class ProgressReader {

    private TeamRepository teamRepository;

    public ProgressReader(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public Progress readProgressFromFile(ForkInformation fork, String content) {
        Progress progress = new Progress(fork.getUsername());
        progress.setUserInFodFin(teamRepository.isUserInFodFin(fork.getUsername()));
        progress.setRealName(teamRepository.getRealName(fork.getUsername()));
        progress.setTeam(teamRepository.getTeam(fork.getUsername()));
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
