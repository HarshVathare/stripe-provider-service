package com.hulkhiretech.payments.stripe_provider_service.http;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

@Builder
@Data
public class HttpRequest {
    private HttpMethod httpMethod;
    private String uri;
    private HttpHeaders headers;
    private Object requestBody;
}
