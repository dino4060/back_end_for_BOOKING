package ute.mobile.back_end_for_BOOKING.business.patterns;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ute.mobile.back_end_for_BOOKING.models.User;
import ute.mobile.back_end_for_BOOKING.models.dto.Role;
import ute.mobile.back_end_for_BOOKING.ui.dto.AuthRes;

import org.springframework.http.HttpHeaders;

@AllArgsConstructor
@Slf4j
public abstract class AuthTemplateImpl implements AuthTemplate {

    private final AuthFacade authFacade;

    // TEMPLATE //

    protected abstract Role getRole();

    // COMMAND //

    @Override
    public AuthRes login(LoginStrategy body, HttpHeaders headers) {
        User user = body.checkBody(authFacade);
        authFacade.checkRole(user, getRole());
        return authFacade.inAuth(user, headers);
    }
}
