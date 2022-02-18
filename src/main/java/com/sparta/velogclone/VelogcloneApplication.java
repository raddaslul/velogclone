package com.sparta.velogclone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class VelogcloneApplication {

    public static void main(String[] args) {
        SpringApplication.run(VelogcloneApplication.class, args);
    }

}
