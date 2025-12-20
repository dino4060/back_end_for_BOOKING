package ute.mobile.back_end_for_BOOKING.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class NoRightsE extends ResponseStatusException {
    public NoRightsE(String message) {
        super(HttpStatus.FORBIDDEN, message);
    }
}
