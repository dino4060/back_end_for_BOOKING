package ute.mobile.back_end_for_BOOKING.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ModelNotFoundE extends ResponseStatusException {
    public ModelNotFoundE(String model) {
        super(HttpStatus.NOT_FOUND, "Not found " + model + ".");
    }
}
