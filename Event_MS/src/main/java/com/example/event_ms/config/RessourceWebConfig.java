package com.example.event_ms.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class RessourceWebConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/upload-directory/**")
                .addResourceLocations("file:./upload-directory/")  // Chemin absolu vers le dossier "upload"
                .setCachePeriod(0);
    }
}
