package com.cegeka.gistexplorer.progresstracker.progress;

import com.cegeka.gistexplorer.progresstracker.gist.Fork;
import com.cegeka.gistexplorer.progresstracker.gist.ForkDetail;
import com.cegeka.gistexplorer.progresstracker.gist.ForkInformation;
import com.cegeka.gistexplorer.progresstracker.gist.GistClient;
import com.cegeka.gistexplorer.progresstracker.teams.TeamMember;
import com.cegeka.gistexplorer.progresstracker.teams.TeamRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.cegeka.gistexplorer.progresstracker.teams.TeamMember.Team.STUDENT;
import static java.util.Comparator.reverseOrder;

@Service
public class ProgressTrackerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProgressTrackerService.class);

    private String fodFinGistId;
    private GistClient gistClient;
    private ProgressReader progressReader;
    private TeamRepository teamRepository;

    public ProgressTrackerService(@Value("${fodfin.gistid}") String fodFinGistId, GistClient gistClient, ProgressReader progressReader, TeamRepository teamRepository) {
        this.fodFinGistId = fodFinGistId;
        this.gistClient = gistClient;
        this.progressReader = progressReader;
        this.teamRepository = teamRepository;
    }

    public List<Progress> trackProgressOfForks() {
        List<Fork> forks = new ArrayList<>();
        forks.addAll(gistClient.getAllForksOfGist(fodFinGistId));
        LOGGER.info("forks found: " + forks);
        return forks.stream()
                .map(Fork::getUrl)
                .map(url -> gistClient.getFork(url))
                .map(this::toProgress)
                .sorted(Comparator.comparing(Progress::getTeam).thenComparing(Progress::getLastUpdate, reverseOrder()).thenComparing(Progress::getRealName))
                .collect(Collectors.toList());
    }

    private Progress toProgress(ForkDetail detail) {
        return progressReader.readProgressFromFile(detail, detail.getContent());
    }

    private Progress toProgress(ForkInformation f, String p) {
        return progressReader.readProgressFromFile(f, p);
    }

    public List<Progress> getProgressOfSelfEvaluation(String base) {
        List<ForkDetail> forks = teamRepository.getAllMembersFromTeam(STUDENT)
                .stream()
                .map(TeamMember::getGithubName)
                .map(gn -> gistClient.getFork(gn))
                .collect(Collectors.toList());

        LOGGER.info("forks found: " + forks);
        return forks.stream()
                .map(f -> getProgress(base, f))
                .collect(Collectors.toList());
    }

    private Progress getProgress(String base, ForkDetail f) {
        String selfEvaluation = gistClient.getSelfEvaluation(f.getOwner().getUsername(), base);
        return toProgress(f, selfEvaluation);
    }

}
