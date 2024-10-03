package vivien.doingthigns.fantasyworldgenerator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class FantasyworldgeneratorApplication {

    public static void main(String[] args) {
        // Start Spring Boot application
        SpringApplication.run(FantasyworldgeneratorApplication.class, args);
    }

}
