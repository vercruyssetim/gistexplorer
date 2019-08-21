package com.cegeka.gistexplorer;

import com.google.common.collect.Lists;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

import static java.lang.String.format;
import static java.nio.charset.StandardCharsets.UTF_8;
import static org.springframework.http.HttpStatus.Series.CLIENT_ERROR;
import static org.springframework.http.HttpStatus.Series.SERVER_ERROR;

@SpringBootApplication
public class GistexplorerApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(GistexplorerApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(GistexplorerApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        LOGGER.info(format("Authorization token: %s", System.getenv("GITHUB_TOKEN")));
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setInterceptors(Lists.newArrayList((request, body, execution) -> {
            request.getHeaders().add("Authorization", String.format("token %s" , System.getenv("GITHUB_TOKEN")));
            request.getHeaders().add("User-Agent", "vercruyssetim");
            return execution.execute(request, body);
        }));
        restTemplate.setErrorHandler(new ResponseErrorHandler() {
            @Override
            public boolean hasError(ClientHttpResponse response) throws IOException {
                return response.getStatusCode().series() == CLIENT_ERROR || response.getStatusCode().series() == SERVER_ERROR;
            }

            @Override
            public void handleError(ClientHttpResponse response) throws IOException {
                LOGGER.error(IOUtils.toString(response.getBody(), UTF_8.name()));
                throw new RuntimeException(response.getStatusCode().toString());
            }
        });
        return restTemplate;
    }

}
