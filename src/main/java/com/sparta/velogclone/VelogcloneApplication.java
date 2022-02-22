package com.sparta.velogclone;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableJpaAuditing
@EnableScheduling
public class VelogcloneApplication {

    public static final String APPLICATION_LOCATIONS = "spring.config.location="
            + "classpath:application.yml,"
            + "classpath:aws.yml";

    public static void main(String[] args) {

//        SpringApplication.run(VelogcloneApplication.class, args);
        new SpringApplicationBuilder(VelogcloneApplication.class)
                .properties(APPLICATION_LOCATIONS)
                .run(args);
    }

}
