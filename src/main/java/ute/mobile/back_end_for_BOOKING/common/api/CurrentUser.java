package ute.mobile.back_end_for_BOOKING.common.api;

import java.util.Set;

import lombok.NonNull;
import ute.mobile.back_end_for_BOOKING.models.User;

public record CurrentUser(
        @NonNull Long id,
        Set<String> roles) {
    public User toUser() {
        var user = new User();
        user.setId(this.id);
        return user;
    }
}