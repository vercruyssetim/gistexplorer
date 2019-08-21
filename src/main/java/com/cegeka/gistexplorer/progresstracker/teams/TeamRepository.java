package com.cegeka.gistexplorer.progresstracker.teams;

import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamRepository {

    private List<String> fodFinUserNames = Lists.newArrayList(
            "simoncdesmet",
            "b0bbie",
            "nickmeert-dev",
            "keving160894",
            "everte",
            "soit0",
            "agerday",
            "dardennt",
            "jensvco",
            "ozlem0101",
            "tomasblond",
            "santiagomc19",
            "nagarashi",
            "jimocket"
    );

    public boolean isUserInFodFin(String username) {
        return fodFinUserNames.contains(username.toLowerCase());
    }
}
