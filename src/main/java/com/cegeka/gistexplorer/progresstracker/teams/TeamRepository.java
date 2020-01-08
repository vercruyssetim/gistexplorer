package com.cegeka.gistexplorer.progresstracker.teams;

import com.cegeka.gistexplorer.progresstracker.teams.TeamMember.Team;
import com.google.common.collect.ImmutableMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

import static com.cegeka.gistexplorer.progresstracker.teams.TeamMember.Team.STUDENT;
import static com.cegeka.gistexplorer.progresstracker.teams.TeamMember.Team.TEACHER;
import static com.cegeka.gistexplorer.progresstracker.teams.TeamMember.teamMember;

@Service
public class TeamRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(TeamRepository.class);

    private Map<String, TeamMember> teamMemberMap = ImmutableMap.<String, TeamMember>builder()
            .put("weareallamazingpeople", teamMember("Karel Van Roey", TEACHER))
            .put("vercruyssetim", teamMember("Tim Vercruysse", TEACHER))
            .put("reinoutvanbets", teamMember("Reinout Van Bets", TEACHER))
            .put("boddz", teamMember("Dries Bodaer", STUDENT))
            .put("nielshendrickx", teamMember("Niels Hendrickx", STUDENT))
            .put("svendp1988", teamMember("Sven De Potter", STUDENT))
            .put("Tombellens", teamMember("Tom Bellens", STUDENT))
            .put("Cemal-y", teamMember("Cemal Yildiz", STUDENT))
            .put("maarteneeckman", teamMember("Maarten Eeckman", STUDENT))
            .put("2", teamMember("Adrien Helin", STUDENT))
            .put("Patriziabattisti", teamMember("Battisti Patrizia", STUDENT))
            .put("MatiasVL", teamMember("Matias Van Langenhove", STUDENT))
            .put("4", teamMember("Harold Brinkhof", STUDENT))
            .put("BramVanMechelen", teamMember("Bram Van Mechelen", STUDENT))
            .put("5", teamMember("Cecilia Szenes", STUDENT))
            .put("SvenVerboven", teamMember("Sven Verboven", STUDENT))
            .build();

    public boolean isUserInFodFin(String username) {
        return Optional.ofNullable(teamMemberMap.get(username)).map(TeamMember::isInFodFin).orElse(false);
    }

    public String getRealName(String username) {
        return Optional.ofNullable(teamMemberMap.get(username)).map(TeamMember::getNaam).orElse("Name not known");
    }

    public Team getTeam(String username) {
        Optional<Team> team = Optional.ofNullable(teamMemberMap.get(username)).map(TeamMember::getTeam);
        if (!team.isPresent()) {
            LOGGER.warn(username + " not found!");
        }
        return team.orElse(STUDENT);
    }
}
