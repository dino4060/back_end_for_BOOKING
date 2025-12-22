package ute.mobile.back_end_for_BOOKING.api.dto;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoomData {
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
  String smartLocation;
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
  List<String> bookedDate;
  List<String> amenities;
}