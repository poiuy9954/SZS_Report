package com.szskimjinho;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jdbc.repository.config.EnableJdbcAuditing;

@SpringBootApplication
@EnableJdbcAuditing
public class SzsKimjinhoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SzsKimjinhoApplication.class, args);
    }

}
