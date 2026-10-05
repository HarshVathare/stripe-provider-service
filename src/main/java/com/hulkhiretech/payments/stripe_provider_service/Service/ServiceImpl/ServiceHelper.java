package com.hulkhiretech.payments.stripe_provider_service.Service.ServiceImpl;

import com.hulkhiretech.payments.stripe_provider_service.Constant.StripeRequestFields;
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

import java.util.List;

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

        headers.setBasicAuth(StripeAPIKey, EMPTY_STRING);
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> requestBody =
                new LinkedMultiValueMap<>();

        // Checkout session details
        requestBody.add(
                StripeRequestFields.MODE,
                StripeRequestFields.PAYMENT
        );

        requestBody.add(
                StripeRequestFields.CANCEL_URL,
                request.getCancelUrl()
        );

        requestBody.add(
                StripeRequestFields.SUCCESS_URL,
                request.getSuccessUrl()
        );

        // Line items
        for (int i = 0; i < request.getLineItems().size(); i++) {

            LineItem item = request.getLineItems().get(i);

            requestBody.add(
                    String.format(
                            StripeRequestFields.LINE_ITEM_QUANTITY,
                            i
                    ),
                    String.valueOf(item.getQuantity())
            );

            requestBody.add(
                    String.format(
                            StripeRequestFields.LINE_ITEM_CURRENCY,
                            i
                    ),
                    item.getCurrency()
            );

            requestBody.add(
                    String.format(
                            StripeRequestFields.LINE_ITEM_PRODUCT_NAME,
                            i
                    ),
                    item.getProductName()
            );

            requestBody.add(
                    String.format(
                            StripeRequestFields.LINE_ITEM_UNIT_AMOUNT,
                            i
                    ),
                    String.valueOf(item.getUnitAmount())
            );
        }

        // Payment intent details
        requestBody.add(
                StripeRequestFields.PAYMENT_INTENT_STATEMENT_DESCRIPTOR,
                request.getBrandName()
        );

        requestBody.add(
                StripeRequestFields.PAYMENT_INTENT_STATEMENT_DESCRIPTOR_SUFFIX,
                request.getBrandName()
        );

        return HttpRequest.builder()
                .httpMethod(HttpMethod.POST)
                .uri(CreateCheckoutSessionURI)
                .headers(headers)
                .requestBody(requestBody)
                .build();
    }
}