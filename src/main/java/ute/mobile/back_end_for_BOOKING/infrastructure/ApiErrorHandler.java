package ute.mobile.back_end_for_BOOKING.infrastructure;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import lombok.extern.slf4j.Slf4j;
import ute.mobile.back_end_for_BOOKING.infrastructure.dto.ApiResponse;

@ControllerAdvice
@Slf4j
public class ApiErrorHandler {
    /**
     * Handle custom exception
     */
    @ExceptionHandler(value = ResponseStatusException.class)
    ResponseEntity<ApiResponse<Object>> handleException(ResponseStatusException e) {
        return ResponseEntity
                .status(e.getStatusCode())
                .body(ApiResponse.builder()
                        .success(false)
                        .code(e.getStatusCode().value())
                        .message(e.getReason())
                        .build());
    }

    /**
     * Handle exception in the validation tier
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<ApiResponse<Object>> handleException(MethodArgumentNotValidException exception) {
        String message = exception.getFieldError() != null
                ? exception.getFieldError().getDefaultMessage()
                : "Dữ liệu không hợp lệ";

        return ResponseEntity
                .status(exception.getStatusCode())
                .body(ApiResponse.builder()
                        .success(false)
                        .code(exception.getStatusCode().value())
                        .message(message)
                        .build());
    }

    /**
     * Handle unhandled app exception
     */

    @ExceptionHandler(value = RuntimeException.class)
    ResponseEntity<ApiResponse<Object>> handleException(RuntimeException exception) {
        log.error(">>> INTERNAL: unhandled exception occurred", exception);
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.builder()
                        .success(false)
                        .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .message("System error unhandled")
                        .build());
    }


    /**
     * Handle exception in the spring security tier
     */
    @ExceptionHandler(value = AccessDeniedException.class)
    ResponseEntity<ApiResponse<Object>> handleException(AccessDeniedException exception) {
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(ApiResponse.builder()
                        .success(false)
                        .code(HttpStatus.FORBIDDEN.value())
                        .message("User has no rights")
                        .build());
    }

    /**
     * Handle exception in the web layer
     */

    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    ResponseEntity<ApiResponse<Object>> handleException(HttpRequestMethodNotSupportedException exception) {;
        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .body(ApiResponse.builder()
                        .success(false)
                        .code(HttpStatus.METHOD_NOT_ALLOWED.value())
                        .message("API route does not support this method")
                        .build());
    }

    @ExceptionHandler(value = NoResourceFoundException.class)
    ResponseEntity<ApiResponse<Object>> handleException(NoResourceFoundException exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.builder()
                        .success(false)
                        .code(HttpStatus.BAD_REQUEST.value())
                        .message("API route does not support this path")
                        .build());
    }

    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    ResponseEntity<ApiResponse<Object>> handleException(HttpMessageNotReadableException exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.builder()
                        .success(false)
                        .code(HttpStatus.BAD_REQUEST.value())
                        .message("API route does not support this body")
                        .build());
    }
}
