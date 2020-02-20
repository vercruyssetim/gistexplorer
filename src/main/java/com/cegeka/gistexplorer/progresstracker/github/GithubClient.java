package com.cegeka.gistexplorer.progresstracker.github;

import com.google.common.collect.ImmutableMap;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class GithubClient {

    private Map<String, String> selfEvaluationMap = ImmutableMap.<String, String>builder()
            .put("1", "10-programming-fundamentals-java")
            .put("2", "20-programming-advanced-java")
            .put("3", "30-enterprise-software-development-java")
            .put("4", "40-databases-orm-java")
            .put("5", "50-web-development-web-frameworks-vaadin-7")
            .build();

    private RestTemplate restTemplate;

    public GithubClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Fork> getAllGistsFor(String userName) {
        return restTemplate
                .exchange(
                        String.format("https://api.github.com/users/%s/gists", userName),
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<Fork>>() {
                        }
                )
                .getBody();
    }

    public Fork getForkForTrackJava(String userName) {
        return restTemplate.getForObject(String.format("https://api.github.com/repos/%s/track-java", userName), Fork.class);
    }

    @Cacheable("selfEvaluation")
    public String getSelfEvaluationContent(String username, String number) {
        return restTemplate.getForObject(
                String.format("https://raw.githubusercontent.com/%s/track-java/master/" + selfEvaluationMap.get(number) + "/00-self-evaluation/README.md", username),
                String.class);
    }

    public String getGistContent(Fork detail) {
        return Optional.ofNullable(detail.getFiles().getFiles().get("feb2020progress.txt"))
                .map(file -> restTemplate.getForObject(file.getRawUrl(), String.class))
                .orElse("");
    }
}
