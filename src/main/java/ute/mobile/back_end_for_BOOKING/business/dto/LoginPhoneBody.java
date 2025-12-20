package ute.mobile.back_end_for_BOOKING.business.dto;

import ute.mobile.back_end_for_BOOKING.business.patterns.AuthFacade;
import ute.mobile.back_end_for_BOOKING.business.patterns.LoginStrategy;
import ute.mobile.back_end_for_BOOKING.models.User;

public record LoginPhoneBody(
                String phone,
                String password) implements LoginStrategy {

        @Override
        public User checkBody(AuthFacade authFacade) {
                var user = authFacade.checkPhone(phone);
                authFacade.checkPassword(user, password);
                return user;
        }
}