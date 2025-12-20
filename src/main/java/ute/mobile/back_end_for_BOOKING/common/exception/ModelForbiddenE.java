package ute.mobile.back_end_for_BOOKING.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ModelForbiddenE extends ResponseStatusException {
    public ModelForbiddenE(String model) {
        super(HttpStatus.FORBIDDEN, "You don't have permission to the " + model + ".");
    }
}
