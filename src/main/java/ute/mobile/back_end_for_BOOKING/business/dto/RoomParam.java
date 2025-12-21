package ute.mobile.back_end_for_BOOKING.business.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;
import ute.mobile.back_end_for_BOOKING.common.application.PageParam;

@EqualsAndHashCode(callSuper = true)
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoomParam extends PageParam {
    Long id;
    String keywords;
    String name;
    String email;
    String phone;
    String username;
}