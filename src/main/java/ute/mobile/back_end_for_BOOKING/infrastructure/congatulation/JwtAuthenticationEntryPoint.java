package ute.mobile.back_end_for_BOOKING.infrastructure.congatulation;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.*;
import lombok.experimental.FieldDefaults;
import tools.jackson.databind.ObjectMapper;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;

/**
 * handle exception threw at Spring Security Filter Chain, specifically
 * unauthenticated
 */
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse httpResponse,
            AuthenticationException authException)
            throws IOException, ServletException {

        // define an httpResponse
        httpResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
        httpResponse.setStatus(HttpStatus.UNAUTHORIZED.value());

        // define an apiResponse
        ApiResClone<?> apiRes = ApiResClone.builder()
                .success(false)
                .code(HttpStatus.UNAUTHORIZED.value())
                .message("User is unauthorized")
                .build();

        // write httpResponse and apiResponse to buffet that will be sent to client
        ObjectMapper objectMapper = new ObjectMapper();
        httpResponse.getWriter().write(objectMapper.writeValueAsString(apiRes));
        httpResponse.flushBuffer();

    }

    @Builder
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @FieldDefaults(level = AccessLevel.PRIVATE)
    private static class ApiResClone<T> {
        boolean success;
        int code;
        String message;
        T data;
    }
}
