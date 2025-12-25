package ute.mobile.back_end_for_BOOKING.business.dto;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoomDataShort {
  @JsonProperty("_id")
  Long _id;
  Long id;
  Instant createdAt;
  Instant updatedAt;
  Boolean isDeleted;
  String name;
  String highlight;
  String detail;
  String transit;
  String houseRules;
  UserData host;
  String street;
  String country;
  String destination;
  BigDecimal latitude;
  BigDecimal longitude;
  String roomType;
  Integer bathRooms;
  Boolean isPrivateBathrooms;
  Integer bedRooms;
  Integer beds;
  Boolean isCoupleBed;
  BigDecimal price;
  BigDecimal weeklyPrice;
  Float reviewStars;
  List<String> thumbnailUrls;

  public void setId(Long id) {
    this.id = id;
    this._id = id;
  }
}