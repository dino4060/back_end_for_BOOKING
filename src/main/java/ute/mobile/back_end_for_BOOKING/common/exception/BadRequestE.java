package ute.mobile.back_end_for_BOOKING.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class BadRequestE extends ResponseStatusException {
    public BadRequestE(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
}
