package com.cegeka.gistexplorer;

import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class GistexplorerApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(GistexplorerApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(GistexplorerApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setInterceptors(Lists.newArrayList((request, body, execution) -> {
            request.getHeaders().add("Authorization", System.getenv("GITHUB_TOKEN"));
            return execution.execute(request, body);
        }));
        return restTemplate;
    }

}
