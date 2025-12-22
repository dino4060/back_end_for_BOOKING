package ute.mobile.back_end_for_BOOKING.business;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import ute.mobile.back_end_for_BOOKING.api.dto.AuthRes;
import ute.mobile.back_end_for_BOOKING.business.dto.RegisterBody;
import ute.mobile.back_end_for_BOOKING.business.patterns.AuthFacade;
import ute.mobile.back_end_for_BOOKING.business.patterns.AuthTemplateImpl;
import ute.mobile.back_end_for_BOOKING.models.User;
import ute.mobile.back_end_for_BOOKING.models.dto.Role;

@Service
@Slf4j
public class AuthService extends AuthTemplateImpl {

    private final AuthFacade authFacade;

    private final UserService userService;

    private final TokenService tokenService;

    public AuthService(
            AuthFacade authFacade, UserService userService, TokenService tokenService) {
        super(authFacade);
        this.authFacade = authFacade;
        this.userService = userService;
        this.tokenService = tokenService;
    }

    protected Role getRole() {
        return Role.CUSTOMER;
    }

    public AuthRes registerCustomer(RegisterBody body, HttpHeaders headers) {
        this.userService.checkEmailNotExists(body.email());
        this.userService.checkPhoneNotExists(body.phone());

        User user = this.userService.createCustomer(
                body.name(), body.email(), body.phone(), body.password());
        this.tokenService.createToken(user);

        return this.authFacade.inAuth(user, headers);
    }
}
