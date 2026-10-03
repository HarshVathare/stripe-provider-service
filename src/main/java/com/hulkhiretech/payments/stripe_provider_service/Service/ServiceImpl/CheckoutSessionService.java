package com.hulkhiretech.payments.stripe_provider_service.Service.ServiceImpl;

import com.hulkhiretech.payments.stripe_provider_service.Pojo.CreateCheckoutSessionRequest;
import com.hulkhiretech.payments.stripe_provider_service.Pojo.CreateCheckoutSessionResponse;
import com.hulkhiretech.payments.stripe_provider_service.Service.ServiceInterfaces.CheckoutSession;
import com.hulkhiretech.payments.stripe_provider_service.http.HttpRequest;
import com.hulkhiretech.payments.stripe_provider_service.http.HttpServiceEngine;
import com.hulkhiretech.payments.stripe_provider_service.util.JsonUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.jackson.autoconfigure.JacksonProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class CheckoutSessionService implements CheckoutSession {

    private final HttpServiceEngine httpServiceEngine;

    private final ServiceHelper serviceHelper;

    private final JsonUtil jsonUtil;

    @Override
    public CreateCheckoutSessionResponse createCheckoutSession(CreateCheckoutSessionRequest request) {
        log.info("Received checkout session in the Service layer");

        HttpRequest httpRequest = serviceHelper.getHttpRequest(request);

        String responseHttp = httpServiceEngine.makeHttpCall(httpRequest);

        log.info("Received response from HttpServiceEngine: {}", responseHttp);

        CreateCheckoutSessionResponse response =
                jsonUtil.convertToObject(
                        responseHttp,
                        CreateCheckoutSessionResponse.class
                );

        log.info("Converted response to CreateCheckoutSessionResponse: {}", response);

        return response;

    }
}
