package ute.mobile.back_end_for_BOOKING.business.dto;

public record RegisterBody(
        String name,
        String phone,
        String email,
        String password
) {
}