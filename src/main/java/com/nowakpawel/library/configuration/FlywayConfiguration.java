package com.nowakpawel.library.configuration;

import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FlywayConfiguration {
    @Bean
    public FlywayMigrationStrategy flywayMigrationStrategy() {
        return flyway -> {

        };
    }
}
