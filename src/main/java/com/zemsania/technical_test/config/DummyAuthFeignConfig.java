package com.zemsania.technical_test.config;

import com.zemsania.technical_test.config.exception.feign.DummyAuthErrorDecoder;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DummyAuthFeignConfig {

    @Bean
    public ErrorDecoder errorDecoder() {
        return new DummyAuthErrorDecoder();
    }
}
