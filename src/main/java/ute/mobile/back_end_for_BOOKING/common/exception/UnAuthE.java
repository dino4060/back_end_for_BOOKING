package ute.mobile.back_end_for_BOOKING.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UnAuthE extends ResponseStatusException {
    public UnAuthE(String message) {
        super(HttpStatus.UNAUTHORIZED, message);
    }
}
