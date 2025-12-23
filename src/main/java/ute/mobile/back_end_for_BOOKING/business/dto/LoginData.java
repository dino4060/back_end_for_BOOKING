package ute.mobile.back_end_for_BOOKING.business.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoginData {

    Boolean isAuthenticated;

    String accessToken;

    UserData currentUser;
}
