package com.example.SpringAPI.Product;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Configuration
public class ProductConfig {
    @Bean
    CommandLineRunner commandLineRunner
            (ProductRepository repository){
        return args -> {
            Product iphone = new Product(
                    "Iphone 13 Pro Max",
                    "6.7-inch (diagonal) all-screen OLED display",
                    999.99,
                    "1631751961000",
                    "1631751961000",
                    "Apple",
                    "Active",
                    Date.from(LocalDateTime.now().toInstant(java.time.ZoneOffset.UTC)),
                    Date.from(LocalDateTime.now().toInstant(java.time.ZoneOffset.UTC))
            );
            Product iphone2 = new Product(
                    "Iphone 12 Pro Max",
                    "6.7-inch (diagonal) all-screen OLED display",
                    999.99,
                    "1631751961000",
                    "https://store.storeimages.cdn-apple.com/4668/as-images.apple.com/is/iphone-13-pro-max-silver-hero?wid=940&hei=1112&fmt=png-alpha&.v=1631751961000",
                    "Apple",
                    "Active",
                    Date.from(LocalDateTime.now().toInstant(java.time.ZoneOffset.UTC)),
                    Date.from(LocalDateTime.now().toInstant(java.time.ZoneOffset.UTC))
            );

            //repository.saveAll(
              //      List.of(iphone)
         //   );
        };
    };

}