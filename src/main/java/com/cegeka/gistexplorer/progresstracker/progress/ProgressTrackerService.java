package com.cegeka.gistexplorer.progresstracker.progress;

import com.cegeka.gistexplorer.progresstracker.gist.Fork;
import com.cegeka.gistexplorer.progresstracker.gist.ForkDetail;
import com.cegeka.gistexplorer.progresstracker.gist.GistClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProgressTrackerService {
    private static Logger LOGGER = LoggerFactory.getLogger(ProgressTrackerService.class);

    private GistClient gistClient;
    private ProgressReader progressReader;

    public ProgressTrackerService(GistClient gistClient, ProgressReader progressReader) {
        this.gistClient = gistClient;
        this.progressReader = progressReader;
    }

    public List<Progress> trackProgressOfForks() {
        List<Fork> forks = gistClient.getAllForksOfGist();
        LOGGER.info("forks found" + forks);
        return forks.stream()
                .map(Fork::getUrl)
                .map(url -> gistClient.getFork(url))
                .map(this::toProgress)
                .sorted(Comparator.comparing(Progress::isUserInFodFin))
                .collect(Collectors.toList());
    }

    private Progress toProgress(ForkDetail detail) {
        return progressReader.readProgressFromFile(detail);
    }

    private Progress toProgress(Fork f, String p) {
        return progressReader.readProgressFromFile(f, p);
    }

    public List<Progress> getProgressOfSelfEvaluation(String base) {
        List<Fork> forks = gistClient.getAllForksOfTrackJava();
        return forks.stream()
                .map(f -> {
                    String selfEvaluation = gistClient.getSelfEvaluation(f.getOwner().getUsername(), base);
                    return toProgress(f, selfEvaluation);
                })
                .sorted(Comparator.comparing(Progress::isUserInFodFin))
                .collect(Collectors.toList());
    }

}
