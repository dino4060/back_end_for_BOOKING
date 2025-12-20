package ute.mobile.back_end_for_BOOKING.infrastructure.dto;

import java.time.Duration;
import java.time.Instant;

public record TokenPair(
        String accessToken,
        Duration accessTokenTtl,
        Instant accessTokenExpiry,
        String refreshToken,
        Duration refreshTokenTtl,
        Instant refreshTokenExpiry) {
}
