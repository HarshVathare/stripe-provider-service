package com.hulkhiretech.payments.stripe_provider_service.Pojo;

import lombok.Data;

@Data
public class LineItem {
    private Integer quantity;
    private String currency;
    private String productName;
    private Integer unitAmount;
}
