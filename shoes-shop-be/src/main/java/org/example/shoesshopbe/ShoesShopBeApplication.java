package org.example.shoesshopbe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@EnableSpringDataWebSupport(pageSerializationMode = EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO)
@SpringBootApplication
public class ShoesShopBeApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShoesShopBeApplication.class, args);
    }

}
