package ute.mobile.back_end_for_BOOKING.infrastructure;

import org.springframework.core.MethodParameter;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import ute.mobile.back_end_for_BOOKING.common.api.AuthUser;
import ute.mobile.back_end_for_BOOKING.common.api.CurrentUser;

@Component
public class AuthUserAnnotationImpl implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {

        return parameter.hasParameterAnnotation(AuthUser.class)
                && parameter.getParameterType().equals(CurrentUser.class);
    }

    @Override
    public Object resolveArgument(
            @SuppressWarnings("deprecation") @NonNull MethodParameter parameter,
            @SuppressWarnings("deprecation") @Nullable ModelAndViewContainer mavContainer,
            @SuppressWarnings("deprecation") @NonNull NativeWebRequest webRequest,
            @SuppressWarnings("deprecation") @Nullable WebDataBinderFactory binderFactory) throws Exception {

        return SecurityUtils.getCurrentUser();
    }
}
