package ute.mobile.back_end_for_BOOKING.business.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import ute.mobile.back_end_for_BOOKING.models.dto.BookingStatus;

@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Slf4j
public class TripBodyPatch {
  @JsonProperty("_id")
  Long _id;
  BookingStatus status;

  public Long getId() {
    return this._id;
  }
}