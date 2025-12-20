package ute.mobile.back_end_for_BOOKING.business.patterns;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ute.mobile.back_end_for_BOOKING.business.TokenService;
import ute.mobile.back_end_for_BOOKING.business.mappers.UserMapper;
import ute.mobile.back_end_for_BOOKING.common.exception.NoRightsE;
import ute.mobile.back_end_for_BOOKING.common.exception.UnAuthE;
import ute.mobile.back_end_for_BOOKING.infrastructure.CookieProvider;
import ute.mobile.back_end_for_BOOKING.infrastructure.SecurityProvider;
import ute.mobile.back_end_for_BOOKING.infrastructure.dto.TokenPair;
import ute.mobile.back_end_for_BOOKING.models.Token;
import ute.mobile.back_end_for_BOOKING.models.User;
import ute.mobile.back_end_for_BOOKING.models.dto.Role;
import ute.mobile.back_end_for_BOOKING.models.repositories.UserRepo;
import ute.mobile.back_end_for_BOOKING.ui.dto.AuthRes;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthFacade {

    private final TokenService tokenService;

    private final UserRepo userRepo;

    private final UserMapper userMapper;

    private final SecurityProvider securityProvider;

    private final CookieProvider cookieProvider;

    // checkUsername //
    public User checkUsername(String username) {
        return this.userRepo.findByUsername(username)
                .orElseThrow(() -> new UnAuthE("Username not found"));
    }

    // checkEmail //
    public User checkEmail(String email) {
        return this.userRepo.findByEmail(email)
                .orElseThrow(() -> new UnAuthE("Email not found"));
    }

    // checkPhone //
    public User checkPhone(String phone) {
        return this.userRepo.findByPhone(phone)
                .orElseThrow(() -> new UnAuthE("Phone not found"));
    }

    // checkPassword //
    public void checkPassword(User user, String password) {
        if (!this.securityProvider.matchPassword(password, user.getPassword()))
            throw new UnAuthE("Password is incorrect");
    }

    // checkRole //
    public void checkRole(User user, Role role) {
        if (!user.getRoles().contains(role.name()))
            throw new NoRightsE("User has no rights");
    }

    // inAuth //
    public AuthRes inAuth(User user, HttpHeaders headers) {
        // get tokens
        TokenPair tokenPair = this.securityProvider.genTokenPair(user);

        // update refresh token to database
        this.tokenService.updateRefreshToken(
                tokenPair.refreshToken(), tokenPair.refreshTokenExpiry(), user.getId());

        // set refresh token to cookie
        this.cookieProvider.attachRefreshToken(
                headers, tokenPair.refreshToken(), tokenPair.refreshTokenTtl());

        var authData = new AuthRes();
        authData.setIsAuthenticated(true);
        authData.setAccessToken(tokenPair.accessToken());
        authData.setCurrentUser(this.userMapper.toData(user));
        return authData;
    }

    // unAuth //
    public AuthRes unAuth(HttpHeaders headers) {
        this.cookieProvider.clearRefreshToken(headers);

        var authData = new AuthRes();
        authData.setIsAuthenticated(false);
        return authData;
    }

    // outAuth //
    public AuthRes outAuth(Token token, HttpHeaders headers) {
        // 1. clean refresh token from DB
        this.tokenService.cleanRefreshToken(token);

        // 2. clean refresh token from cookies
        this.cookieProvider.clearRefreshToken(headers);

        var authData = new AuthRes();
        authData.setIsAuthenticated(true);
        return authData;
    }
}
