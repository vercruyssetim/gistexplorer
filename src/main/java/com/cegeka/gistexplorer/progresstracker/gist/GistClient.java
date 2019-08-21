package com.cegeka.gistexplorer.progresstracker.gist;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class GistClient {

    private String gistId;

    private RestTemplate restTemplate;

    public GistClient(@Value("${gistid}") String gistId, RestTemplate restTemplate) {
        this.gistId = gistId;
        this.restTemplate = restTemplate;
    }

    public ForkDetail getFork(String url) {
        return restTemplate.getForObject(url, ForkDetail.class);
    }

    public List<Fork> getAllForksOfGist() {
        return restTemplate
                .exchange(
                        String.format("https://api.github.com/gists/%s/forks", gistId),
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<Fork>>() {
                        }
                )
                .getBody();
    }

    public List<Fork> getAllForksOfTrackJava() {
        return restTemplate
                .exchange(
                        String.format("https://api.github.com/repos/switchfully/track-java/forks"),
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<Fork>>() {
                        }
                )
                .getBody();
    }

    public String getSelfEvaluation(String username, String base) {
        return restTemplate.getForObject(
                String.format("https://raw.githubusercontent.com/%s/track-java/master/" + base + "/00-self-evaluation/README.md", username),
                String.class);
    }
}
