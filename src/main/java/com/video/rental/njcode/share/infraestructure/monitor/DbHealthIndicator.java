package com.video.rental.njcode.share.infraestructure.monitor;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Random;

@Component
public class DbHealthIndicator implements HealthIndicator {


    @Override
    public Health health() {
        if( isDbHealthy()){
            return Health.up().withDetails(Map.of("DB Service", "Healthy")).build();
        }

        return Health.down().withDetails(Map.of("DB Service", "Not Healthy")).build();
    }

    private boolean isDbHealthy() {
        Random random = new Random();
        return random.nextBoolean();
    }
}
