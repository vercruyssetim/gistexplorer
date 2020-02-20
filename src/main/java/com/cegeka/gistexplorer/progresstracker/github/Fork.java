package com.cegeka.gistexplorer.progresstracker.github;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Fork {

    @JsonProperty("html_url")
    private String htmlUrl;

    @JsonProperty("updated_at")
    private LocalDate lastUpdate;

    private Owner owner;

    private Files files;

    public Owner getOwner() {
        return owner;
    }

    public Files getFiles() {
        return files;
    }

    public String getUsername() {
        return owner.getUsername();
    }

    public LocalDate getLastUpdate() {
        return lastUpdate;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public static class Files {
        Map<String, File> files = new HashMap<>();

        @JsonAnyGetter
        public Map<String, File> getFiles() {
            return files;
        }

        @JsonAnySetter
        public void setFiles(String name, File file){
            files.put(name, file);
        }

        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class File {
            private String content;
            @JsonProperty("raw_url")
            private String rawUrl;

            public String getContent() {
                return content;
            }

            public String getRawUrl() {
                return rawUrl;
            }
        }
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
