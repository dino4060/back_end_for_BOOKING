package ute.mobile.back_end_for_BOOKING.ui.dto;

import java.time.Instant;
import java.util.Set;

import ute.mobile.back_end_for_BOOKING.models.dto.Role;

public record UserData(
                Long id,
                String name,
                String username,
                String email,
                String phone,
                String status,
                Set<Role> roles,
                Integer provinceId,
                Integer wardId,
                String street,
                Instant createdAt,
                Instant updatedAt) {
}
