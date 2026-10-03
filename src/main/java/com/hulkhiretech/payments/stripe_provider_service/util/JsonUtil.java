package com.hulkhiretech.payments.stripe_provider_service.util;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

import java.util.LinkedHashMap;

@Component
@Slf4j
@RequiredArgsConstructor
public class JsonUtil {

    private final ObjectMapper objectMapper;

    public String convertToJson(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            log.error("Error converting object to JSON: {}", e.getMessage());
//            throw new PaymentValidationException(
//                    ErrorCodeEnum.OBJ_TO_JSON_CONVERSION_ERROR.getErrorCode(),
//                    ErrorCodeEnum.OBJ_TO_JSON_CONVERSION_ERROR.getErrorMessage(),
//                    ErrorCodeEnum.OBJ_TO_JSON_CONVERSION_ERROR.getHttpStatus()
//            );

            throw new RuntimeException();
        }
    }

    //convert json to object
    public <T> T convertToObject(String json, Class<T> clazz) {
        try {
            return objectMapper.readValue(json, clazz);
        } catch (Exception e) {
            log.error("Error converting JSON to object: {}", e.getMessage());
//            throw new PaymentValidationException(
//                    ErrorCodeEnum.JSON_TO_OBJ_CONVERSION_ERROROBJ_TO_JSON_CONVERSION_ERROR.getErrorCode(),
//                    ErrorCodeEnum.JSON_TO_OBJ_CONVERSION_ERROROBJ_TO_JSON_CONVERSION_ERROR.getErrorMessage(),
//                    ErrorCodeEnum.JSON_TO_OBJ_CONVERSION_ERROROBJ_TO_JSON_CONVERSION_ERROR.getHttpStatus()
//            );
            throw new RuntimeException();
        }
    }

    public String prepareFormattedJson(String body) {
        if (body == null || body.isBlank()) {
            return "";
        }

        try {
            // Parse JSON while preserving order
            LinkedHashMap<String, Object> map =
                    objectMapper.readValue(body, LinkedHashMap.class);
            // Serialize back to JSON
            return objectMapper.writeValueAsString(map);
        } catch (Exception e) {
            log.error("Error while formatting JSON body: {}", e.getMessage(), e);
//            throw new PaymentValidationException(
//                    ErrorCodeEnum.JSON_STRING_FORMATTING_ERROR.getErrorCode(),
//                    ErrorCodeEnum.JSON_STRING_FORMATTING_ERROR.getErrorMessage(),
//                    ErrorCodeEnum.JSON_STRING_FORMATTING_ERROR.getHttpStatus()
//            );
            throw new RuntimeException();

        }
    }

}
