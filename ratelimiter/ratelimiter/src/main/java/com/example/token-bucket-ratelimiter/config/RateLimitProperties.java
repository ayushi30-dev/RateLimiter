
package com.example.ratelimiter.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "rate-limit")
public class RateLimitProperties {

    private long capacity;
    private long refillTokens;
    private long refillDurationSeconds;

    public long getCapacity() {
        return capacity;
    }

    public void setCapacity(long capacity) {
        this.capacity = capacity;
    }

    public long getRefillTokens() {
        return refillTokens;
    }

    public void setRefillTokens(long refillTokens) {
        this.refillTokens = refillTokens;
    }

    public long getRefillDurationSeconds() {
        return refillDurationSeconds;
    }

    public void setRefillDurationSeconds(
            long refillDurationSeconds) {
        this.refillDurationSeconds = refillDurationSeconds;
    }
}
