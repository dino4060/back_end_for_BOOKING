package ute.mobile.back_end_for_BOOKING.infrastructure;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PUBLIC)
public class EnvProvider {
    // JWT //

    @Value("${jwt.access.secret-key}")
    String ACCESS_SECRET_KEY;

    @Value("${jwt.access.ttl-min}")
    Long ACCESS_TTL_MIN;

    @Value("${jwt.refresh.secret-key}")
    String REFRESH_SECRET_KEY;

    @Value("${jwt.refresh.ttl-days}")
    Long REFRESH_TTL_DAYS;

}
