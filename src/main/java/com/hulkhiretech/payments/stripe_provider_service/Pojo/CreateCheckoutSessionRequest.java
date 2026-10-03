package com.hulkhiretech.payments.stripe_provider_service.Pojo;

import lombok.Data;

import java.util.List;

@Data
public class CreateCheckoutSessionRequest {
    private String cancelUrl;
    private String successUrl;
    private String brandName;
    private List<LineItem> lineItems;
}
