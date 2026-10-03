package com.hulkhiretech.payments.stripe_provider_service.Service.ServiceImpl;

import com.hulkhiretech.payments.stripe_provider_service.Pojo.CreateCheckoutSessionRequest;
import com.hulkhiretech.payments.stripe_provider_service.Pojo.LineItem;
import com.hulkhiretech.payments.stripe_provider_service.http.HttpRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Service
@Slf4j
@RequiredArgsConstructor
public class ServiceHelper {

    private static final String EMPTY_STRING = "";

    @Value("${stripe.api.key}")
    private String StripeAPIKey;

    @Value("${checkout.session.uri}")
    private String CreateCheckoutSessionURI;

    public HttpRequest getHttpRequest(CreateCheckoutSessionRequest request) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(StripeAPIKey,EMPTY_STRING);
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);


        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();

        requestBody.add("mode", "payment");
        requestBody.add("cancel_url", request.getCancelUrl());
        requestBody.add("success_url", request.getSuccessUrl());

        for (int i = 0; i < request.getLineItems().size(); i++) {
            LineItem item = request.getLineItems().get(i);

            requestBody.add("line_items[" + i + "][quantity]", item.getQuantity().toString());
            requestBody.add("line_items[" + i + "][price_data][currency]", item.getCurrency());
            requestBody.add("line_items[" + i + "][price_data][product_data][name]", item.getProductName());
            requestBody.add("line_items[" + i + "][price_data][unit_amount]", item.getUnitAmount().toString());
        }


        requestBody.add(
                "payment_intent_data[statement_descriptor]",
                request.getBrandName()
        );

        requestBody.add(
                "payment_intent_data[statement_descriptor_suffix]",
                request.getBrandName()
        );


        HttpRequest httpRequest = HttpRequest.builder()
                .httpMethod(HttpMethod.POST)
                .uri(CreateCheckoutSessionURI)
                .headers(headers)
                .requestBody(requestBody)
                .build();
        return httpRequest;
    }
}
