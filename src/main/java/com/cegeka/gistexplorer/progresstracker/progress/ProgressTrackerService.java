package com.cegeka.gistexplorer.progresstracker.progress;

import com.cegeka.gistexplorer.progresstracker.github.Fork;
import com.cegeka.gistexplorer.progresstracker.github.GithubClient;
import com.cegeka.gistexplorer.progresstracker.teams.TeamMember;
import com.cegeka.gistexplorer.progresstracker.teams.TeamRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.cegeka.gistexplorer.progresstracker.teams.TeamMember.Team.STUDENT;
import static java.util.Comparator.reverseOrder;

@Service
public class ProgressTrackerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProgressTrackerService.class);

    private String fodFinGistId;
    private GithubClient githubClient;
    private ProgressReader progressReader;
    private TeamRepository teamRepository;

    public ProgressTrackerService(@Value("${fodfin.gistid}") String fodFinGistId, GithubClient githubClient, ProgressReader progressReader, TeamRepository teamRepository) {
        this.fodFinGistId = fodFinGistId;
        this.githubClient = githubClient;
        this.progressReader = progressReader;
        this.teamRepository = teamRepository;
    }

    public List<Progress> getProgressOfGists() {
        return teamRepository.getAllMembersFromTeam(STUDENT)
                .stream()
                .map(TeamMember::getGithubName)
                .map(gn -> githubClient.getAllGistsFor(gn))
                .filter(gists -> !gists.isEmpty())
                .map(gists -> gists.get(0))
                .map(this::toProgress)
                .sorted(Comparator.comparing(Progress::getTeam).thenComparing(Progress::getLastUpdate, reverseOrder()).thenComparing(Progress::getRealName))
                .collect(Collectors.toList());
    }

    private Progress toProgress(Fork fork) {
        return progressReader.readProgressFromFile(fork, githubClient.getGistContent(fork));
    }

    public List<Progress> getProgressOfSelfEvaluation(String number) {
        return teamRepository.getAllMembersFromTeam(STUDENT)
                .stream()
                .map(TeamMember::getGithubName)
                .map(gn -> githubClient.getForkForTrackJava(gn))
                .map(f -> toProgress(f, number))
                .sorted(Comparator.comparing(Progress::getTeam).thenComparing(Progress::getLastUpdate, reverseOrder()).thenComparing(Progress::getRealName))
                .collect(Collectors.toList());
    }

    private Progress toProgress(Fork fork, String number) {
        return progressReader.readProgressFromFile(fork, githubClient.getSelfEvaluationContent(fork.getOwner().getUsername(), number));
    }

}
