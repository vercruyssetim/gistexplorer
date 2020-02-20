package com.cegeka.gistexplorer.progresstracker.gist;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class GistClient {

    private RestTemplate restTemplate;

    public GistClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ForkDetail getFork(String url) {
        return restTemplate.getForObject(url, ForkDetail.class);
    }

    public ForkDetail getForkForUser(String userName) {
        return getFork(String.format("https://api.github.com/repos/%s/track-java", userName));
    }

    public List<Fork> getAllForksOfGist(String gistId) {
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

    public List<Fork> getAllForksOfTrackJava(int page) {
        return restTemplate
                .exchange(
                        String.format("https://api.github.com/repos/switchfully/track-java/forks?page=%s", page),
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<Fork>>() {
                        }
                )
                .getBody();
    }

    @Cacheable("selfEvaluation")
    public String getSelfEvaluation(String username, String base) {
        return restTemplate.getForObject(
                String.format("https://raw.githubusercontent.com/%s/track-java/master/" + base + "/00-self-evaluation/README.md", username),
                String.class);
    }
}
