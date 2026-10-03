package com.hulkhiretech.payments.stripe_provider_service.http;

import com.hulkhiretech.payments.stripe_provider_service.util.JsonUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;

import java.util.function.Consumer;

@Component
@Slf4j
@RequiredArgsConstructor
public class HttpServiceEngine {

    private final RestClient restClient;

    public String makeHttpCall(HttpRequest httpRequest ){

        ResponseEntity<String> httpresponse = restClient.method(httpRequest.getHttpMethod())
                .uri(httpRequest.getUri())
                .headers((restClienthttpHeaders) -> restClienthttpHeaders.addAll(httpRequest.getHeaders()))
                .body(httpRequest.getRequestBody())

                .retrieve()
                .toEntity(String.class);

        log.info("Http call made Successfully ..! {}", httpresponse);

        return httpresponse.getBody();
    }
}
