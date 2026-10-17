
package com.example.ratelimiter.service;

import com.example.ratelimiter.config.RateLimitProperties;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Service
public class RateLimiterService {

    private final ConcurrentMap<String, Bucket> buckets =
            new ConcurrentHashMap<>();

    private final RateLimitProperties properties;

    public RateLimiterService(RateLimitProperties properties) {
        this.properties = properties;
    }

    private Bucket createBucket() {
        Refill refill = Refill.greedy(
                properties.getRefillTokens(),
                Duration.ofSeconds(
                        properties.getRefillDurationSeconds()));

        Bandwidth limit = Bandwidth.classic(
                properties.getCapacity(), refill);

        return Bucket.builder()
                .addLimit(limit)
                .build();
    }

    public boolean isAllowed(String clientId) {
        Bucket bucket = buckets.computeIfAbsent(
                clientId, key -> createBucket());

        return bucket.tryConsume(1);
    }
}
