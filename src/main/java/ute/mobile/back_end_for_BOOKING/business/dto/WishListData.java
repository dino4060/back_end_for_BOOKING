package ute.mobile.back_end_for_BOOKING.business.dto;

import java.time.Instant;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WishListData {
  @JsonProperty("_id")
  Long _id;
  Long id;
  Instant createdAt;
  Instant updatedAt;
  Boolean isDeleted;
  Integer count;
  List<LikedRoomData> likedRooms;

  public void setId(Long id) {
    this.id = id;
    this._id = id;
  }
}