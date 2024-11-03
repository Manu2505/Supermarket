package de.group2.supermarket.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
     public class WebConfig implements WebMvcConfigurer {

         @Override
         public void addCorsMappings(@SuppressWarnings("null") CorsRegistry registry) {
             registry.addMapping("/**") // Allow CORS for all paths
                     .allowedOrigins("http://localhost:5173")
                     .allowedMethods("GET", "POST", "PUT", "DELETE")
                     .allowedHeaders("*");
         }
     }