package com.trade.project;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
@Slf4j
public class ProjectApplication {
    public static void main(String[] args) {
        log.debug("start app");
        new SpringApplicationBuilder(ProjectApplication.class).run(args);
    }

}
