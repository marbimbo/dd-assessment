package com.misio.client;

import com.misio.model.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import javax.annotation.PostConstruct;
import java.lang.invoke.MethodHandles;

@Component
public class PersonClient {

    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private WebClient client;
    private String baseUrl;

    @Value("${baseUrl}")
    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @PostConstruct
    private void initWebClient() {
        client = WebClient.create(baseUrl);
    }

    public void send(Person p) {
        WebClient.ResponseSpec responseSpec = client.post()
                .uri(baseUrl)
                .bodyValue(p)
                .retrieve();

        responseSpec.toBodilessEntity();
    }
}
