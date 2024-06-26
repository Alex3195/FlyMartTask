package org.alexkings.productdeliveryapi;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class ProductDeliveryApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductDeliveryApiApplication.class, args);
    }

    @Bean
    @Qualifier("passwordEncoderMap")
    public Map<String, PasswordEncoder> passwordEncoders() {
        Map<String, PasswordEncoder> encoders = new HashMap<>();
        encoders.put("bcrypt", new BCryptPasswordEncoder());
        encoders.put("scrypt", SCryptPasswordEncoder.defaultsForSpringSecurity_v5_8());
        return encoders;
    }
    @Bean
    public PasswordEncoder delegatingPasswordEncoder() {
        String idForEncode = "bcrypt"; // Default encoder
        Map<String, PasswordEncoder> encoders = new HashMap<>();
        encoders.put("bcrypt", new BCryptPasswordEncoder());
        encoders.put("scrypt", SCryptPasswordEncoder.defaultsForSpringSecurity_v5_8());

        return new DelegatingPasswordEncoder(idForEncode, encoders);
    }

}
