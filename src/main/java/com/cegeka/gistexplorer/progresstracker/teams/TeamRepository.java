package com.cegeka.gistexplorer.progresstracker.teams;

import com.cegeka.gistexplorer.progresstracker.teams.TeamMember.Team;
import com.google.common.collect.ImmutableMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.cegeka.gistexplorer.progresstracker.teams.TeamMember.Team.STUDENT;
import static com.cegeka.gistexplorer.progresstracker.teams.TeamMember.Team.TEACHER;
import static com.cegeka.gistexplorer.progresstracker.teams.TeamMember.teamMember;

@Service
public class TeamRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(TeamRepository.class);

    private Map<String, TeamMember> teamMemberMap = ImmutableMap.<String, TeamMember>builder()
            .put("weareallamazingpeople", teamMember("Karel Van Roey", "weareallamazingpeople", TEACHER))
            .put("vercruyssetim", teamMember("Tim Vercruysse", "vercruyssetim", TEACHER))
            .put("reinoutvanbets", teamMember("Reinout Van Bets", "reinoutvanbets", TEACHER))
            .put("Akagami1030", teamMember("Omar Lamrabet", "Akagami1030", STUDENT))
            .put("FitzSilk", teamMember("Adrien Helin", "FitzSilk", STUDENT))
            .put("boddz", teamMember("Dries Bodaer", "boddz", STUDENT))
            .put("BramVanMechelen", teamMember("Bram Van Mechelen", "BramVanMechelen", STUDENT))
            .put("Cemal-y", teamMember("Cemal Yildiz", "Cemal-y", STUDENT))
            .put("CeciliaSzenes", teamMember("Cecilia Szenes", "CeciliaSzenes", STUDENT))
            .put("Govjo", teamMember("Jonathan Govaert", "Govjo", STUDENT))
            .put("haroldbrinkhof", teamMember("Harold Brinkhof", "haroldbrinkhof", STUDENT))
            .put("maarteneeckman", teamMember("Maarten Eeckman", "maarteneeckman", STUDENT))
            .put("MatiasVL", teamMember("Matias Van Langenhove", "MatiasVL", STUDENT))
            .put("nielshendrickx", teamMember("Niels Hendrickx", "nielshendrickx", STUDENT))
            .put("Patriziabattisti", teamMember("Battisti Patrizia", "Patriziabattisti", STUDENT))
            .put("smilo227", teamMember("Serge Milo", "smilo227", STUDENT))
            .put("svendp1988", teamMember("Sven De Potter", "svendp1988", STUDENT))
            .put("SvenVerboven", teamMember("Sven Verboven", "SvenVerboven", STUDENT))
            .put("TheRealTomDC", teamMember("Tom Decrock", "TheRealTomDC", STUDENT))
            .put("tjangxu", teamMember("Tjang Nan Xu", "tjangxu", STUDENT))
            .put("Tombellens", teamMember("Tom Bellens", "Tombellens", STUDENT))
            .put("TWENS-VDAB", teamMember("Thomas Wens", "TWENS-VDAB", STUDENT))
            .put("T-Castro", teamMember("Tine Castro", "T-Castro", STUDENT))
            .build();

    public boolean isUserInFodFin(String username) {
        return Optional.ofNullable(teamMemberMap.get(username)).map(TeamMember::isInFodFin).orElse(false);
    }

    public String getRealName(String username) {
        return Optional.ofNullable(teamMemberMap.get(username)).map(TeamMember::getName).orElse("Name not known");
    }

    public Team getTeam(String username) {
        Optional<Team> team = Optional.ofNullable(teamMemberMap.get(username)).map(TeamMember::getTeam);
        if (!team.isPresent()) {
            LOGGER.warn(username + " not found!");
        }
        return team.orElse(STUDENT);
    }

    public List<TeamMember> getAllMembersFromTeam(Team team) {
        return teamMemberMap.values()
                .stream()
                .filter(tm -> tm.getTeam().equals(team))
                .collect(Collectors.toList());
    }
}
