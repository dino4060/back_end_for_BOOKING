package ute.mobile.back_end_for_BOOKING.business.dto;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TripData {
  @JsonProperty("_id")
  Long _id;
  Long id;
  Instant createdAt;
  Instant updatedAt;
  Boolean isDeleted;
  UserData customer;
  RoomData room;
  LocalDate startDate;
  LocalDate endDate;
  Instant bookingTime;
  BigDecimal total;
  List<LocalDate> bookedDates;
  String status;

  public void setId(Long id) {
    this.id = id;
    this._id = id;
  }
}