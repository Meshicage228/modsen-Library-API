package by.meshicage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "by.meshicage.client")
public class BookStorageApplication {
    public static void main(String[] args) {
        SpringApplication.run(BookStorageApplication.class, args);
    }
}