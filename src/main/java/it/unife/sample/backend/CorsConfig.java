package it.unife.sample.backend; 

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // Copre tutti gli URL del backend
                        .allowedOrigins("http://localhost:4200") // Permette l'accesso ad Angular
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Abilita tutti i metodi necessari
                        .allowedHeaders("*") // Permette qualsiasi Header (es. Content-Type)
                        .allowCredentials(true); // Consente lo scambio di cookie o sessioni se necessario
            }
        };
    }
}
