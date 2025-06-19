package com.zemsania.technical_test.config.exception.feign;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zemsania.technical_test.config.exception.ExternalApiException;
import feign.Response;
import feign.codec.ErrorDecoder;

import java.io.IOException;

public class DummyAuthErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        String message = extractMessage(response);
        return new ExternalApiException(response.status(), message);
    }

    private String extractMessage(Response response) {
        try {
            if (response.body() != null) {
                return new ObjectMapper()
                        .readTree(response.body().asInputStream())
                        .path("message")
                        .asText("Error en servicio externo");
            }
        } catch (IOException e) {
            return "Error procesando respuesta del servicio externo";
        }
        return "Error desconocido en servicio externo";
    }
}
