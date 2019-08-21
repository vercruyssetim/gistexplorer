package com.cegeka.gistexplorer.progresstracker.gist;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Fork {

    private String url;

    private Fork() {}

    public String getUrl() {
        return url;
    }


}
