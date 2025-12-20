package ute.mobile.back_end_for_BOOKING.infrastructure;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.Jwt;

import ute.mobile.back_end_for_BOOKING.common.api.CurrentUser;
import ute.mobile.back_end_for_BOOKING.common.application.Id;
import ute.mobile.back_end_for_BOOKING.common.exception.UnAuthE;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public final class SecurityUtils {

    private SecurityUtils() {
    }

    /**
     * getAuthentication
     *
     * @des get authentication from Security Context
     */
    private static Optional<Authentication> getAuthentication() {
        // 1. get Security Context
        var authentication = SecurityContextHolder.getContext().getAuthentication();

        // 2. can be null
        if (authentication == null || !authentication.isAuthenticated()) {
            return Optional.empty();
        }
        return Optional.of(authentication);
    }

    /**
     * extractPrincipal
     *
     * @des extract the subject, a claim of jwt payload, from jwt principal
     */
    private static Optional<String> extractPrincipal(Authentication authentication) {
        // Code: authentication.getPrincipal() instanceof Jwt jwt
        // Mean: if authentication.getPrincipal() has the Jwt type, assign it to the jwt
        // variable
        String subject = null;

        if (authentication.getPrincipal() instanceof UserDetails springSecurityUser) {
            subject = springSecurityUser.getUsername();
        } else if (authentication.getPrincipal() instanceof Jwt jwt) {
            subject = jwt.getSubject();
        } else if (authentication.getPrincipal() instanceof String string) {
            subject = string;
        }

        return Optional.ofNullable(subject);
    }

    /**
     * getCurrentUserId
     */
    private static Id getCurrentUserId() {
        return getAuthentication()
                .flatMap(auth -> extractPrincipal(auth))
                .flatMap(subject -> {
                    if (subject.equals("anonymousUser"))
                        return Id.from("0");
                    else
                        return Id.from(subject);
                })
                .orElseThrow(() -> new UnAuthE("Cannot get current user from security context"));
    }

    /**
     * getCurrentUserRoles
     */
    private static Set<String> getCurrentUserRoles() {
        // 1. get Security Context
        var authentication = getAuthentication();

        // 2. get the roles, a claim of jwt payload
        return authentication
                .map(value -> value.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toSet()))
                .orElse(null);
    }

    /**
     * getCurrentUser
     *
     * @des Get the current user from the jwt payload
     */
    public static CurrentUser getCurrentUser() {
        return new CurrentUser(getCurrentUserId().value(), getCurrentUserRoles());
    }
}
