package com.cegeka.gistexplorer.progresstracker.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ForkOwner {

    @JsonProperty("login")
    private String username;

    private ForkOwner() {}

    private ForkOwner(ForkOwnerBuilder forkOwnerBuilder) {
        this.username = forkOwnerBuilder.username;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public String toString() {
        return "ForkOwner{" +
                "username='" + username + '\'' +
                '}';
    }

    public static class ForkOwnerBuilder {
        private String username;

        private ForkOwnerBuilder() {
        }

        public static ForkOwnerBuilder forkOwner() {
            return new ForkOwnerBuilder();
        }

        public ForkOwner build() {
            return new ForkOwner(this);
        }

        public ForkOwnerBuilder withUsername(String username) {
            this.username = username;
            return this;
        }
    }
}
