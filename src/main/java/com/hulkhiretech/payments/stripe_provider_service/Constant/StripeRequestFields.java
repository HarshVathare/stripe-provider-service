package com.hulkhiretech.payments.stripe_provider_service.Constant;

public final class StripeRequestFields {

    private StripeRequestFields() {
    }

    public static final String MODE = "mode";
    public static final String PAYMENT = "payment";

    public static final String CANCEL_URL = "cancel_url";
    public static final String SUCCESS_URL = "success_url";

    public static final String LINE_ITEM_QUANTITY =
            "line_items[%d][quantity]";

    public static final String LINE_ITEM_CURRENCY =
            "line_items[%d][price_data][currency]";

    public static final String LINE_ITEM_PRODUCT_NAME =
            "line_items[%d][price_data][product_data][name]";

    public static final String LINE_ITEM_UNIT_AMOUNT =
            "line_items[%d][price_data][unit_amount]";

    public static final String PAYMENT_INTENT_STATEMENT_DESCRIPTOR =
            "payment_intent_data[statement_descriptor]";

    public static final String PAYMENT_INTENT_STATEMENT_DESCRIPTOR_SUFFIX =
            "payment_intent_data[statement_descriptor_suffix]";
}