package ute.mobile.back_end_for_BOOKING.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class DuplicationE extends ResponseStatusException {
    public DuplicationE(String message) {
        super(HttpStatus.CONFLICT, message);
    }
}
