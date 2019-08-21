package com.cegeka.gistexplorer.progresstracker.progress;

import com.cegeka.gistexplorer.progresstracker.gist.ForkDetail;
import com.cegeka.gistexplorer.progresstracker.gist.Fork;
import com.cegeka.gistexplorer.progresstracker.gist.GistClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

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
                .collect(Collectors.toList());
    }

    private Progress toProgress(ForkDetail detail) {
        return progressReader.readProgressFromFile(detail);
    }
}
