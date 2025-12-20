package ute.mobile.back_end_for_BOOKING.infrastructure;

import jakarta.servlet.http.HttpServletResponse;
import ute.mobile.back_end_for_BOOKING.infrastructure.dto.ApiResponse;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@ControllerAdvice
public class ApiDataHandler implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(
            @SuppressWarnings("deprecation") @NonNull MethodParameter returnType,
            @SuppressWarnings("deprecation") @NonNull  Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(
            @SuppressWarnings("deprecation") @Nullable Object body,
            @SuppressWarnings("deprecation") @NonNull MethodParameter returnType,
            @SuppressWarnings("deprecation") @NonNull MediaType selectedContentType,
            @SuppressWarnings("deprecation") @NonNull Class<? extends HttpMessageConverter<?>> selectedConverterType,
            @SuppressWarnings("deprecation") @NonNull ServerHttpRequest request,
            @SuppressWarnings("deprecation") @NonNull ServerHttpResponse response) {
        // case: body is a string
        if (!MediaType.APPLICATION_JSON.equals(selectedContentType))
            return body;
        // case: throw exception
        HttpServletResponse servletResponse = ((ServletServerHttpResponse) response).getServletResponse();
        int apiCode = servletResponse.getStatus();
        if (apiCode >= 400)
            return body;
        // case: swagger
        String path = request.getURI().getPath();
        if (path.startsWith("/v3/api-docs") || path.startsWith("/swagger-ui"))
            return body;
        // case: success
        return ApiResponse.builder()
                .success(true)
                .code(1)
                .data(body)
                .build();
    }
}
