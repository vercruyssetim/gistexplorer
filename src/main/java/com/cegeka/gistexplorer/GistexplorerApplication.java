package com.cegeka.gistexplorer;

import com.google.common.collect.Lists;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class GistexplorerApplication {

    public static void main(String[] args) {
        SpringApplication.run(GistexplorerApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setInterceptors(Lists.newArrayList((request, body, execution) -> {
            request.getHeaders().add("Authorization", "token ebbdd58bd4260cb116fb6b06e270cbe9a6a0011b");
            return execution.execute(request, body);
        }));
        return restTemplate;
    }

}
