package com.hulkhiretech.payments.stripe_provider_service.Service.ServiceInterfaces;

import com.hulkhiretech.payments.stripe_provider_service.Pojo.CreateCheckoutSessionRequest;
import com.hulkhiretech.payments.stripe_provider_service.Pojo.CreateCheckoutSessionResponse;
import org.springframework.http.ResponseEntity;

public interface CheckoutSession {

    CreateCheckoutSessionResponse createCheckoutSession(CreateCheckoutSessionRequest request);
}
