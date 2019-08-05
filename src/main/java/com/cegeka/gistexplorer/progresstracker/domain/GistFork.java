package com.cegeka.gistexplorer.progresstracker.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GistFork {

    private String url;

    @JsonProperty("git_pull_url")
    private String cloneUrl;

    private ForkOwner owner;

    @JsonProperty("updated_at")
    private LocalDate lastUpdate;

    private GistFork() {}

    private GistFork(GistForkBuilder builder) {
        url = builder.url;
        cloneUrl = builder.cloneUrl;
        owner = builder.owner;
    }

    public String getUrl() {
        return url;
    }

    public GistFork withUrl(String url) {
        this.url = url;
        return this;
    }

    public String getCloneUrl() {
        return cloneUrl;
    }

    public GistFork withCloneUrl(String cloneUrl) {
        this.cloneUrl = cloneUrl;
        return this;
    }

    public ForkOwner getOwner() {
        return owner;
    }

    public GistFork withOwner(ForkOwner owner) {
        this.owner = owner;
        return this;
    }

    public LocalDate getLastUpdate() {
        return lastUpdate;
    }

    public static class GistForkBuilder {
        private String url;
        private String cloneUrl;
        private ForkOwner owner;

        private GistForkBuilder() {
        }

        public static GistForkBuilder gistFork() {
            return new GistForkBuilder();
        }

        public GistFork build() {
            return new GistFork(this);
        }

        public GistForkBuilder withUrl(String url) {
            this.url = url;
            return this;
        }

        public GistForkBuilder withCloneUrl(String cloneUrl) {
            this.cloneUrl = cloneUrl;
            return this;
        }

        public GistForkBuilder withOwner(ForkOwner owner) {
            this.owner = owner;
            return this;
        }
    }
}
