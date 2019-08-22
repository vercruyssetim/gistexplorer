package com.cegeka.gistexplorer.progresstracker.teams;

import com.google.common.collect.ImmutableMap;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

import static com.cegeka.gistexplorer.progresstracker.teams.TeamMember.Team.*;
import static com.cegeka.gistexplorer.progresstracker.teams.TeamMember.teamMember;

@Service
public class TeamRepository {

    private Map<String, TeamMember> teamMemberMap = ImmutableMap.<String, TeamMember>builder()
            .put("weareallamazingpeople", teamMember("Karel Van Roey", TEACHER))
            .put("vercruyssetim", teamMember("Tim Vercruysse", TEACHER))
            .put("agerday", teamMember("Adrien Gerday", FOD_FIN))
            .put("b0bbie", teamMember("Bobette de Vries", FOD_FIN))
            .put("dardennt", teamMember("Thomas Dardenne", FOD_FIN))
            .put("everte", teamMember("Evert Edel", FOD_FIN))
            .put("hgjd", teamMember("Hans Dewitte", FOD_FIN))
            .put("initconsult", teamMember("Nick Joosen", FOD_FIN))
            .put("jensvco", teamMember("Jens Van Coppernolle", FOD_FIN))
            .put("jimocket", teamMember("Jim Ocket", FOD_FIN))
            .put("keving160894", teamMember("Kevin Gillet", FOD_FIN))
            .put("nagarashi", teamMember("Kai van Landschoot", FOD_FIN))
            .put("nickmeert-dev", teamMember("Nick Meert", FOD_FIN))
            .put("ozlem0101", teamMember("Ozlem Ozer", FOD_FIN))
            .put("santiagomc19", teamMember("Santiago Mendez", FOD_FIN))
            .put("simoncdesmet", teamMember("Simon Desmet", FOD_FIN))
            .put("soit0", teamMember("Maxime Jofes", FOD_FIN))
            .put("yanvkhv", teamMember("Yannick Vankerkhove", CM))
            .put("dr-brains", teamMember("Laurens Vercauteren", CM))
            .put("maartenvi", teamMember("Maarten van Isterdael", CM))
            .put("lemairedev", teamMember("Nick Lemaire", CM))
            .put("andylandtsheer", teamMember("Andy Landtsheer", CM))
            .put("maxgaj", teamMember("Maxime Gaj", CM))
            .put("ada-dom", teamMember("Adriana Domanowska", CM))
            .put("agoy-beep", teamMember("Arne Goyvaerts", CM))
            .put("mxvb", teamMember("Maxime Verbinnen", CM))
            .put("doebime", teamMember("Danny Doubbel", CM))
            .put("petalexdev", teamMember("Alexis Petre", CM))
            .put("utredc", teamMember("Cedric Marechal", CM))
            .put("tomleebe", teamMember("Tom de Blaes", CM))
            .put("fracresc", teamMember("Francesca Crescini", CM))
            .put("kayvine", teamMember("Kevin Delme", CM))
            .build();

    public boolean isUserInFodFin(String username) {
        return Optional.ofNullable(teamMemberMap.get(username.toLowerCase())).map(TeamMember::isInFodFin).orElse(false);
    }

    public String getRealName(String username) {
        return Optional.ofNullable(teamMemberMap.get(username.toLowerCase())).map(TeamMember::getNaam).orElse("");
    }

    public TeamMember.Team getTeam(String username) {
        return Optional.ofNullable(teamMemberMap.get(username.toLowerCase())).map(TeamMember::getTeam).orElse(CM);
    }
}
