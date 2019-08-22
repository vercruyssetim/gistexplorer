package com.cegeka.gistexplorer.progresstracker.gist;

import java.time.LocalDate;

public interface ForkInformation {
    String getUsername();

    LocalDate getLastUpdate();

    String getHtmlUrl();
}
