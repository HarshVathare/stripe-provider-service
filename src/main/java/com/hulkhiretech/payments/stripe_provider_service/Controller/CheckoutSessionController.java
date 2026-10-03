package com.hulkhiretech.payments.stripe_provider_service.Controller;

import com.hulkhiretech.payments.stripe_provider_service.Pojo.CreateCheckoutSessionRequest;
import com.hulkhiretech.payments.stripe_provider_service.Pojo.CreateCheckoutSessionResponse;
import com.hulkhiretech.payments.stripe_provider_service.Service.ServiceImpl.CheckoutSessionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/checkout/session")
@Slf4j
@RequiredArgsConstructor
public class CheckoutSessionController {

    private final CheckoutSessionService checkoutSessionService;

    @PostMapping
    public ResponseEntity<CreateCheckoutSessionResponse> CreateCheckoutSession(@RequestBody CreateCheckoutSessionRequest request){
        log.info("Receive the checkout session");
        log.info("Call the checkout session to the service layer");
        CreateCheckoutSessionResponse responseDto = checkoutSessionService.createCheckoutSession(request);
        log.info("Received response from Service {}", responseDto);
        return ResponseEntity.ok(responseDto);
    }
}
