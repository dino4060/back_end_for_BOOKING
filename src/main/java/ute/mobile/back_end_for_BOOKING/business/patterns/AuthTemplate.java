package ute.mobile.back_end_for_BOOKING.business.patterns;

import org.springframework.http.HttpHeaders;

import ute.mobile.back_end_for_BOOKING.ui.dto.AuthRes;

public interface AuthTemplate {
    AuthRes login(LoginStrategy body, HttpHeaders headers);
}
