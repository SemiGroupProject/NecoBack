package com.trade.project;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class ProjectApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(ProjectApplication.class).run(args);
    }

}
