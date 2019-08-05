package com.cegeka.gistexplorer.progresstracker.service;

import com.cegeka.gistexplorer.progresstracker.domain.GistFork;
import com.cegeka.gistexplorer.progresstracker.domain.Progress;
import com.cegeka.gistexplorer.progresstracker.util.ProgressFileReaderUtil;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import static com.cegeka.gistexplorer.progresstracker.util.DeleteFolderUtil.removeFolder;

@Service
public class ProgressTrackerService {
    private static Logger LOGGER = LoggerFactory.getLogger(ProgressTrackerService.class);

    protected static final String FORKS_LIST_URL = "https://api.github.com/gists/%s/forks";
    private static final String CLONE_DIRECTORY_PATH = "clonedGist";
    private static final String FILE_NAME = "switchfully-javafin-summer-progress-tracker";

    @Value("${gistid}")
    private String gistId;

    @Autowired
    private RestTemplate restTemplate;

    public List<Progress> trackProgressOfForks() {
        List<GistFork> forks = getAllForksOfGist();
        LOGGER.info("forks found" + forks);
        return forks.stream()
                .map(fork -> {
                    cloneGist(fork);
                    Progress progress = ProgressFileReaderUtil.readProgressFromFile(fork, CLONE_DIRECTORY_PATH);
                    progress.calculateProgress();
                    removeFolder(Paths.get(CLONE_DIRECTORY_PATH).toFile());
                    return progress;
                })
                .collect(Collectors.toList());
    }

    private void cloneGist(GistFork fork) {
        try {
            Git clone = Git.cloneRepository()
                    .setURI(fork.getCloneUrl())
                    .setDirectory(Paths.get(CLONE_DIRECTORY_PATH).toFile())
                    .call();
            clone.getRepository().close();
        } catch (GitAPIException e) {
            e.printStackTrace();
        }
    }

    private List<GistFork> getAllForksOfGist() {
        return restTemplate
                .exchange(
                        String.format(FORKS_LIST_URL, gistId),
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<GistFork>>() {
                        }
                )
                .getBody();
    }
}
