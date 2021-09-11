package com.trade.project;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@Slf4j
public class ProjectApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(ProjectApplication.class).run(args);
    }

}
