package com.cegeka.gistexplorer.progresstracker.teams;

import java.util.Objects;

import static com.cegeka.gistexplorer.progresstracker.teams.TeamMember.Team.STUDENT;

public class TeamMember {
    private final String naam;
    private String githubName;
    private final Team team;

    private TeamMember(String naam, String githubName, Team team) {
        this.naam = naam;
        this.githubName = githubName;
        this.team = team;
    }

    public static TeamMember teamMember(String naam, String githubName, Team team){
        return new TeamMember(naam, githubName, team);
    }

    public boolean isInFodFin() {
        return team == STUDENT;
    }

    public enum Team {
        STUDENT,
        TEACHER
    }

    public String getName() {
        return naam;
    }

    public Team getTeam() {
        return team;
    }

    public String getGithubName() {
        return githubName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeamMember that = (TeamMember) o;
        return Objects.equals(naam.toLowerCase(), that.naam.toLowerCase()) &&
                team == that.team;
    }

    @Override
    public int hashCode() {
        return Objects.hash(naam.toLowerCase(), team);
    }
}
