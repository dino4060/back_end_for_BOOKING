package ute.mobile.back_end_for_BOOKING.business.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;
import ute.mobile.back_end_for_BOOKING.common.application.PageParam;

@EqualsAndHashCode(callSuper = true)
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserParam extends PageParam {
    Long id;
    String name;
    String email;
    String phone;
    String username;
}