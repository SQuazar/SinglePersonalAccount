package net.quazar.backend;

import net.quazar.backend.service.FileUploadService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@ConfigurationPropertiesScan
public class SinglePersonalAccountApplication {

    public static void main(String[] args) {
        SpringApplication.run(SinglePersonalAccountApplication.class, args);
    }

    @Bean
    CommandLineRunner init(FileUploadService service) {
        return args -> service.init();
    }
}
