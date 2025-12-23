package ute.mobile.back_end_for_BOOKING.business.dto;

public record LoginRegisterBody(
        String name,
        String phone,
        String email,
        String password
) {
}