package com.hulkhiretech.payments.stripe_provider_service.Pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class CreateCheckoutSessionResponse {

    private String id;

    private String url;

}
