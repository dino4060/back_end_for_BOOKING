package ute.mobile.back_end_for_BOOKING.business.patterns;

import ute.mobile.back_end_for_BOOKING.models.User;

public interface LoginStrategy {
    User checkBody(AuthFacade authFacade);
}
