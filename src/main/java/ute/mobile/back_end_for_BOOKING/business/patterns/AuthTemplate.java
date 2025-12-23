package ute.mobile.back_end_for_BOOKING.business.patterns;

import org.springframework.http.HttpHeaders;

import ute.mobile.back_end_for_BOOKING.business.dto.LoginData;

public interface AuthTemplate {
    LoginData login(LoginStrategy body, HttpHeaders headers);
}
