package com.acko.htmlgenerator.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@Profile({"local","default", "uat","dev"})
public class CorsConfig implements WebMvcConfigurer {


    @Override
    public void addCorsMappings(final CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(
                        "http://localhost:3000",
                        "http://localhost:6006",
                        "https://partnership-presentation-layer.internal.ackodev.com",
                        "http://localhost:3006",
                        "https://netdu.acko.com",
                        "https://www.ackodev.com",
                        "https://partnership-presentation-layer-dev.internal.ackodev.com",
                        "https://partner-portal.corp.acko.com",
                        "https://partnership-presentation-layer.internal.live.acko.com"
                )
                .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}
