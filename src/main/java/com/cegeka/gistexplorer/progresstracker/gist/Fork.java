package com.cegeka.gistexplorer.progresstracker.gist;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Fork {
    @JsonProperty("html_url")
    private String htmlUrl;

    @JsonProperty("updated_at")
    private LocalDate lastUpdate;

    private String url;

    private Owner owner;

    private Fork() {}

    public String getUrl() {
        return url;
    }

    public Owner getOwner() {
        return owner;
    }

    public LocalDate getLastUpdate() {
        return lastUpdate;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    @Override
    public String toString() {
        return "Fork of " + owner.username;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Owner {

        @JsonProperty("login")
        private String username;

        private Owner() {}

        public String getUsername() {
            return username;
        }

        @Override
        public String toString() {
            return "Owner{" +
                    "username='" + username + '\'' +
                    '}';
        }
    }

}
