package ute.mobile.back_end_for_BOOKING.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class NotFoundE extends ResponseStatusException {
    public NotFoundE(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}
