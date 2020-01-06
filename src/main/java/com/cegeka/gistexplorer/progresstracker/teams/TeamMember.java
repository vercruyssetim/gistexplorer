package com.cegeka.gistexplorer.progresstracker.teams;

import static com.cegeka.gistexplorer.progresstracker.teams.TeamMember.Team.STUDENT;

public class TeamMember {
    private final String naam;
    private final Team team;

    private TeamMember(String naam, Team team) {
        this.naam = naam;
        this.team = team;
    }

    public static TeamMember teamMember(String naam, Team team){
        return new TeamMember(naam, team);
    }

    public boolean isInFodFin() {
        return team == STUDENT;
    }

    public enum Team {
        STUDENT,
        TEACHER
    }

    public String getNaam() {
        return naam;
    }

    public Team getTeam() {
        return team;
    }
}
