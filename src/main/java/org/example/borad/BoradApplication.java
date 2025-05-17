package org.example.borad;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class BoradApplication {

    public static void main(String[] args) {
        SpringApplication.run(BoradApplication.class, args);
    }

}
