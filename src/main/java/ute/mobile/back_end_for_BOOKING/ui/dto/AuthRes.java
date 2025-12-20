package ute.mobile.back_end_for_BOOKING.ui.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthRes {

    Boolean isAuthenticated;

    String accessToken;

    UserData currentUser;
}
