
package com.example.ratelimiter.controller;

import com.example.ratelimiter.service.RateLimiterService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class DemoController {

    private final RateLimiterService rateLimiterService;

    public DemoController(RateLimiterService rateLimiterService) {
        this.rateLimiterService = rateLimiterService;
    }

    @GetMapping("/api/demo")
    public ResponseEntity<Map<String, String>> demo(
            HttpServletRequest request) {

        String clientIp = request.getRemoteAddr();

        if (!rateLimiterService.isAllowed(clientIp)) {
            return ResponseEntity
                    .status(HttpStatus.TOO_MANY_REQUESTS)
                    .body(Map.of(
                            "error", "Rate limit exceeded",
                            "message", "Maximum 5 requests per minute. Please try again later."
                    ));
        }

        return ResponseEntity.ok(Map.of(
                "message", "Request successful!",
                "clientIp", clientIp
        ));
    }
}
