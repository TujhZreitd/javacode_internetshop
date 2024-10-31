package javacode.javacode_internetshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("javacode.javacode_internetshop.model")
@EnableJpaRepositories(basePackages = "javacode.javacode_internetshop")
public class InternetRestApplication {
    public static void main(String[] args) {
        SpringApplication.run(InternetRestApplication.class, args);
    }
}
