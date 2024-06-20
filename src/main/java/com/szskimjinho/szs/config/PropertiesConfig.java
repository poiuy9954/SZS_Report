package com.szskimjinho.szs.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.ResourcePropertySource;

import java.io.IOException;

@Configuration
@PropertySources({
        @PropertySource("classpath:keys/jwt.properties")
})
public class PropertiesConfig {
    @Bean
    public ResourcePropertySource SecretkeyProperties(Environment env) throws IOException {
        return new ResourcePropertySource("jwt","classpath:keys/jwt.properties");
    }
}
