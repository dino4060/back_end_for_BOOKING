package ute.mobile.back_end_for_BOOKING.business.dto;

import org.springframework.web.bind.annotation.BindParam;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;
import ute.mobile.back_end_for_BOOKING.common.application.PageParam;

@EqualsAndHashCode(callSuper = true)
@Data
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoomParam extends PageParam {
  String destination;

  @BindParam("min-price")
  Integer minPrice;

  @BindParam("max-price")
  Integer maxPrice;

}